import os
import re
import sys
from datetime import datetime
import cv2
import numpy as np
from PyPDF2 import PdfReader, PdfWriter
from PIL import Image
import fitz  # PyMuPDF

# Function for natural sorting
def natural_sort_key(s):
    return [int(text) if text.isdigit() else text.lower()
            for text in re.split(r'(\d+)', s)]

# Function to find valid PDF files in the current directory
def find_valid_pdfs():
    pdf_files = [f for f in os.listdir() if f.endswith('.pdf')]
    num_pdfs = len(pdf_files)

    if num_pdfs == 0:
        print("No valid PDF files found in the current directory.")
    else:
        print(f"Found {num_pdfs} valid PDF files in the current directory.")

    return pdf_files

# Function to generate a default output folder based on input PDF
def generate_default_output_folder(input_pdf):
    timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
    input_filename = os.path.splitext(os.path.basename(input_pdf))[0]
    return os.path.join(os.path.dirname(input_pdf), f"{timestamp}_{input_filename}")

# Function to split a PDF file into individual pages
def split_pdf(input_pdf, output_folder):
    pdf_reader = PdfReader(input_pdf)
    input_filename = os.path.splitext(os.path.basename(input_pdf))[0]
    os.makedirs(output_folder, exist_ok=True)

    num_pages = len(pdf_reader.pages)
    print(f"Processing '{input_filename}' with {num_pages} pages.")

    created_folders = []

    for page_num, page in enumerate(pdf_reader.pages):
        output_filename = f'{input_filename}_page_{page_num + 1}.pdf'
        output_path = os.path.join(output_folder, output_filename)
        write_page_to_pdf(page, pdf_reader.metadata, output_path)

    created_folders.append(output_folder)

    return created_folders

# Function to write a single page to a PDF
def write_page_to_pdf(page, metadata, output_path):
    pdf_writer = PdfWriter()
    pdf_writer.add_page(page)
    pdf_writer.add_metadata(metadata)

    with open(output_path, 'wb') as output_pdf:
        pdf_writer.write(output_pdf)

# Function to list folder locations and PDF counts
def list_folder_info(folders):
    for folder in folders:
        pdf_files = [f for f in os.listdir(folder) if f.endswith('.pdf')]
        image_files = [f for f in os.listdir(folder) if f.endswith('.jpg')]

        num_pdfs = len(pdf_files)
        num_images = len(image_files)

        print(f"Folder: {folder}, PDF Count: {num_pdfs}, Image Count: {num_images}")

# Function to extract pages from PDFs
def extract_pages(pdf_files):
    created_folders = []
    for pdf_file in pdf_files:
        created_folders.extend(split_pdf(pdf_file, generate_default_output_folder(pdf_file)))
    print("Extraction complete.")
    return created_folders

# Function to convert PDF pages to images using PyMuPDF (fitz) and PIL
def convert_to_images(folders):
    for folder in folders:
        pdf_files = [f for f in os.listdir(folder) if f.endswith('.pdf')]
        for pdf_file in pdf_files:
            pdf_path = os.path.join(folder, pdf_file)
            doc = fitz.open(pdf_path)
            for page_num in range(doc.page_count):
                process_page(folder, pdf_file, page_num, doc)

# Function to process an image from a PDF page
def process_page(folder, pdf_file, page_num, doc):
    page = doc[page_num]
    pixmap = page.get_pixmap(matrix=fitz.Matrix(200 / 72, 200 / 72))
    img = Image.frombytes("RGB", [pixmap.width, pixmap.height], pixmap.samples)
    img_path = os.path.join(folder, f"{os.path.splitext(pdf_file)[0]}_page_{page_num + 1}.jpg")
    img.save(img_path, 'JPEG')

# Function to treat images with different filters and resize if needed
def treat_images(folders):
    for folder in folders:
        pdf_files = [f for f in os.listdir(folder) if f.endswith('.pdf')]
        jpg_files = [f for f in os.listdir(folder) if f.endswith('.jpg')]
        treated_files = [f for f in jpg_files if f.startswith('treated_')]

        for pdf_file in pdf_files:
            pdf_path = os.path.join(folder, pdf_file)

            with fitz.open(pdf_path) as doc:
                for page_num in range(doc.page_count):
                    process_image(folder, pdf_file, page_num)

        # Remove all PDF and JPG files except those with the "treated_" prefix
        for file in pdf_files + jpg_files:
            file_path = os.path.join(folder, file)
            if file not in treated_files:
                os.remove(file_path)

# Function to process an image
def process_image(folder, pdf_file, page_num, quality=35, max_width=2000):
    pdf_path = os.path.join(folder, pdf_file)
    doc = fitz.open(pdf_path)
    page = doc[page_num]
    pixmap = page.get_pixmap(matrix=fitz.Matrix(200 / 72, 200 / 72))
    img = Image.frombytes("RGB", [pixmap.width, pixmap.height], pixmap.samples)
    img_path = os.path.join(folder, f"{os.path.splitext(pdf_file)[0]}_page_{page_num + 1}.jpg")

    try:
        # Set a higher limit for Image.MAX_IMAGE_PIXELS
        Image.MAX_IMAGE_PIXELS = None  # Set to None for no limit, or a specific value

        # Load the image using the adapted load_image function
        image = np.array(img)

        if image is not None:
            # Check if the image width is greater than the maximum allowed width
            if image.shape[1] > max_width:
                # Calculate the proportional height based on the maximum width
                ratio = max_width / image.shape[1]
                new_height = int(image.shape[0] * ratio)

                # Resize the image while ensuring the new width is less than or equal to the maximum width
                if new_height > 0:  # Ensure new_height is positive to avoid errors
                    image = cv2.resize(image, (max_width, new_height))

            # Convert the image to grayscale for noise removal (optional)
            gray_image = cv2.cvtColor(image, cv2.COLOR_RGB2GRAY)

            # First pass of noise removal using GaussianBlur
            denoised_image = cv2.GaussianBlur(gray_image, (5, 5), 0)

            # Apply Bilateral Filter for noise reduction
            bilateral_filtered_image = cv2.bilateralFilter(denoised_image, d=8, sigmaColor=20, sigmaSpace=20)

            # Sharpen the denoised image
            sharpened_image = sharpen_image(bilateral_filtered_image)

            # Convert the sharpened image back to RGB
            rgb_image = cv2.cvtColor(sharpened_image, cv2.COLOR_GRAY2RGB)

            # Compressed image filename with 'treated' prefix
            output_filename = "treated_" + os.path.splitext(os.path.basename(img_path))[0] + ".jpg"

            # Full path for the compressed image
            output_path = os.path.join(folder, output_filename)

            # Compress the sharpened image using Pillow
            img = Image.fromarray(rgb_image)
            img.save(output_path, 'JPEG', quality=quality)

        else:
            print(f"Error! Skipping file: {img_path}")
    except Exception as e:
        print(f"Error processing file {img_path}: {e}")

# Function to sharpen an image
def sharpen_image(image):
    # Apply a Laplacian sharpening filter
    kernel = np.array([[-1, -1, -1],
                       [-1,  9, -1],
                       [-1, -1, -1]])
    sharpened_image = cv2.filter2D(image, -1, kernel)
    return sharpened_image

# Function to convert JPG files to individual PDFs
def convert_images_to_pdf(created_folders):
    try:
        for folder_path in created_folders:
            jpg_files = [f for f in os.listdir(folder_path) if f.lower().endswith(".jpg")]

            if not jpg_files:
                print(f"No JPG files found in the folder: {folder_path}")
                continue

            jpg_files.sort(key=natural_sort_key)

            for jpg_file in jpg_files:
                jpg_file_path = os.path.join(folder_path, jpg_file)
                image = Image.open(jpg_file_path)

                pdf_file_path = jpg_file_path.replace(".jpg", ".pdf")
                image.save(pdf_file_path, "PDF")

            # Remove all JPG files after converting them to PDF
            for jpg_file in jpg_files:
                jpg_file_path = os.path.join(folder_path, jpg_file)
                os.remove(jpg_file_path)

    except Exception as e:
        print(f"Error converting images to PDF in folder {folder_path}: {e}")

# Function to merge PDFs into a single PDF
def merge_pdfs(created_folders):
    try:
        for folder_path in created_folders:
            pdf_files = [f for f in os.listdir(folder_path) if f.lower().endswith(".pdf")]

            if not pdf_files:
                print(f"No PDF files found in the folder: {folder_path}")
                continue

            if len(pdf_files) == 1:
                print(f"Only one PDF file found in the folder: {folder_path}. Renaming the file.")
                pdf_file = pdf_files[0]
                old_path = os.path.join(folder_path, pdf_file)
                new_path = os.path.join(folder_path, f"{os.path.basename(folder_path)}.pdf")
                os.rename(old_path, new_path)
                continue

            pdf_files.sort(key=natural_sort_key)

            pdf_document = fitz.open()

            # Use the folder name as the base name for the merged PDF
            folder_name = os.path.basename(folder_path)
            output_pdf_file = os.path.join(folder_path, f"{folder_name}_merged.pdf")

            for pdf_file in pdf_files:
                pdf_file_path = os.path.join(folder_path, pdf_file)
                pdf_document.insert_pdf(fitz.open(pdf_file_path), from_page=0)

                # Remove individual PDFs after merging
                os.remove(pdf_file_path)

            pdf_document.save(output_pdf_file)
            pdf_document.close()

    except Exception as e:
        print(f"Error merging PDFs in folder {folder_path}: {e}")
        if os.path.exists(output_pdf_file):
            os.remove(output_pdf_file)
            print(f"Removed incomplete merged file: '{output_pdf_file}'")


# Function to list files in a folder
def list_files(folder_path):
    files = os.listdir(folder_path)
    files.sort(key=natural_sort_key)
    for file in files:
        print(file)

# Function to handle user choice
def handle_user_choice(pdf_files, created_folders):
    while True:
        try:
            user_choice = int(input("Choose an option:\n1. Extract Pages\n2. Convert to Images\n3. Treat Images\n4. Convert Images to PDF\n5. Merge PDFs\n9. List Folder Info\n0. Exit\n"))

            if user_choice == 1:
                created_folders = extract_pages(pdf_files)
            elif user_choice == 2:
                if not created_folders:
                    print("No folders created yet. Please extract pages first.")
                else:
                    convert_to_images(created_folders)
                    print("Conversion to images complete.")
            elif user_choice == 3:
                if not created_folders:
                    print("No folders created yet. Please extract or convert pages first.")
                else:
                    treat_images(created_folders)
                    print("Image treatment complete.")
            elif user_choice == 4:
                if not created_folders:
                    print("No folders created yet. Please extract or convert pages first.")
                else:
                    convert_images_to_pdf(created_folders)
                    print("Conversion to PDFs complete.")
            elif user_choice == 5:
                if not created_folders:
                    print("No folders created yet. Please extract or convert pages first.")
                else:
                    merge_pdfs(created_folders)
                    print("Merging PDFs complete.")
            elif user_choice == 9:
                if not created_folders:
                    print("No folders created yet.")
                else:
                    list_folder_info(created_folders)
            elif user_choice == 0:
                print("Exiting the script.")
                sys.exit()
            else:
                print("Invalid option. Please try again.")
        except ValueError:
            print("Invalid input. Please enter a valid number.")

# Main function to initiate the process
def main():
    try:
        pdf_files = find_valid_pdfs()
        created_folders = []  # Variable to store created folders

        handle_user_choice(pdf_files, created_folders)

    except Exception as e:
        print(f"An error occurred: {e}")

    input("Press any key to quit...")

if __name__ == '__main__':
    main()
