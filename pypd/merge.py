
# xipspace

import os
import fitz  # PyMuPDF
from PIL import Image

def natural_sort_key(s):
    """A natural sort key function for sorting file names with numeric parts correctly."""
    import re
    return [int(text) if text.isdigit() else text.lower()
            for text in re.split(r'(\d+)', s)]

def convert_jpg_to_pdf(folder_path):
    # List all JPG files in the folder
    jpg_files = [f for f in os.listdir(folder_path) if f.lower().endswith(".jpg")]

    if not jpg_files:
        print("No JPG files found in the specified folder.")
        return

    # Sort the files using the natural sort key function
    jpg_files.sort(key=natural_sort_key)

    # Create a PDF document
    pdf_document = fitz.open()

    for jpg_file in jpg_files:
        jpg_file_path = os.path.join(folder_path, jpg_file)

        # Convert the JPG file to PDF using Pillow
        image = Image.open(jpg_file_path)
        pdf_file_path = jpg_file_path.replace(".jpg", ".pdf")
        image.save(pdf_file_path, "PDF")

        print(f"Converting {jpg_file} to PDF...")

        pdf_document.insert_pdf(fitz.open(pdf_file_path), from_page=0)
        os.remove(pdf_file_path)  # Remove the temporary PDF file

    # Output PDF file name
    output_pdf_file = os.path.join(folder_path, jpg_files[0].replace(".jpg", ".pdf"))

    # Save the merged PDF
    pdf_document.save(output_pdf_file)
    pdf_document.close()

if __name__ == "__main__":
    current_directory = os.getcwd()
    convert_jpg_to_pdf(current_directory)
    print("Conversion and merging completed.")
