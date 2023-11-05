
# xipspace

import os
import argparse
import fitz  # Import PyMuPDF library
from PIL import Image

def convert_pdf_page_to_image(pdf_page, output_folder, prefix, page_number):
    img = pdf_page.get_pixmap(matrix=fitz.Matrix(200 / 72, 200 / 72))
    image = Image.frombytes("RGB", [img.width, img.height], img.samples)
    
    image_path = os.path.join(output_folder, f"{prefix}_{page_number}.jpg")
    
    image.save(image_path)
    print(f"Image saved to {image_path}")

def process_pdf_file(pdf_file, output_folder):
    pdf_name = os.path.splitext(os.path.basename(pdf_file))[0]
    pdf_document = fitz.open(pdf_file)

    if len(pdf_document) == 1:
        # If the PDF has only one page, save the image with the prefix as the filename
        output_folder = os.path.dirname(pdf_file)
        prefix = pdf_name
    else:
        os.makedirs(output_folder, exist_ok=True)  # Create a subfolder with the same name as the PDF
        prefix = pdf_name

    page_number = 0
    for pdf_page in pdf_document:
        page_number += 1
        convert_pdf_page_to_image(pdf_page, output_folder, prefix, page_number)

def process_pdf_files(input_folder):
    pdf_files = [f for f in os.listdir(input_folder) if f.lower().endswith(".pdf")]
    if not pdf_files:
        print("No PDF files found in the input folder.")
        return

    for pdf_file in pdf_files:
        pdf_path = os.path.join(input_folder, pdf_file)
        output_folder = os.path.join(input_folder, os.path.splitext(pdf_file)[0])
        process_pdf_file(pdf_path, output_folder)

def main():
    parser = argparse.ArgumentParser(description="Convert PDFs to images.")
    parser.add_argument("input", nargs="?", default=None, help="Input folder containing PDFs")
    args = parser.parse_args()

    if args.input is None:
        script_directory = os.path.dirname(os.path.abspath(__file__))
        process_pdf_files(script_directory)
    else:
        process_pdf_files(args.input)

    input("Press any key to quit...")

if __name__ == "__main__":
    main()
