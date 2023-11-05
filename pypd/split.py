
# xipspace

from PyPDF2 import PdfReader, PdfWriter
import argparse
import os
from datetime import datetime

# Split a PDF file into individual pages
def split_pdf(input_pdf, output_folder):
    # Read the input PDF
    pdf_reader = PdfReader(input_pdf)
    
    # Get the base filename from the input PDF
    input_filename = os.path.splitext(os.path.basename(input_pdf))[0]
    
    # Create the output folder if it doesn't exist
    os.makedirs(output_folder, exist_ok=True)

    # Iterate through pages and save each page as a separate PDF
    for page_num, page in enumerate(pdf_reader.pages):
        output_filename = f'{input_filename}_page_{page_num + 1}.pdf'
        output_path = os.path.join(output_folder, output_filename)

        # Write the current page to a new PDF
        write_page_to_pdf(page, pdf_reader.metadata, output_path)

    return output_folder

# Write a single page to a PDF
def write_page_to_pdf(page, metadata, output_path):
    pdf_writer = PdfWriter()
    pdf_writer.add_page(page)
    pdf_writer.add_metadata(metadata)

    with open(output_path, 'wb') as output_pdf:
        pdf_writer.write(output_pdf)

# Generate a default output folder based on input PDF
def generate_default_output_folder(input_pdf):
    timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
    input_filename = os.path.splitext(os.path.basename(input_pdf))[0]
    return os.path.join(os.path.dirname(input_pdf), f"{timestamp}_{input_filename}")

# Find and split PDFs in a directory or specified PDF
def find_and_split_pdfs(input_pdf, output_folder):
    if input_pdf is None:
        # List all PDF files in the current directory
        pdf_files = [f for f in os.listdir() if f.endswith('.pdf')]
        if not pdf_files:
            print("No PDF files found in the current directory.")
        else:
            print(f"Found {len(pdf_files)} PDF files in the current directory:")
            for pdf_file in pdf_files:
                print(pdf_file)
                # Split each PDF found
                split_pdf(pdf_file, output_folder or generate_default_output_folder(pdf_file))
    else:
        # Split the specified PDF
        split_pdf(input_pdf, output_folder or generate_default_output_folder(input_pdf))

# Main function to parse command line arguments and initiate the process
def main():
    parser = argparse.ArgumentParser(description="Split a PDF file into individual pages.")
    parser.add_argument('input_pdf', nargs='?', help="Input PDF file to split")
    parser.add_argument('-o', '--output-folder', help="Output folder for individual pages", default=None)
    args = parser.parse_args()
    input_pdf = args.input_pdf
    output_folder = args.output_folder

    try:
        find_and_split_pdfs(input_pdf, output_folder)
        print("Success.")
    except Exception as e:
        print(f"An error occurred: {e}")

    input("Press Enter to exit.")

if __name__ == '__main__':
    main()
