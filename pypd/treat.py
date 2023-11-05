
# xipspace

import os
import cv2
from PIL import Image, ImageShow
import numpy as np

def load_image(input_path):
    try:
        image = cv2.imread(input_path)
        if image is None:
            raise Exception("Failed to load the image. Check the file path or integrity.")
        return image
    except Exception as e:
        print(f"Error loading the image: {e}")
        return None

def sharpen_image(image):
    # Apply a Laplacian sharpening filter
    kernel = np.array([[-1, -1, -1],
                       [-1,  9, -1],
                       [-1, -1, -1]])
    sharpened_image = cv2.filter2D(image, -1, kernel)
    return sharpened_image

def process_image(input_path, output_dir, quality=90):
    image = load_image(input_path)

    if image is not None:
        # Convert the image to grayscale for noise removal (optional)
        gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

        # Remove noise using a denoising filter (e.g., GaussianBlur)
        denoised_image = cv2.GaussianBlur(gray_image, (5, 5), 0)

        # Sharpen the denoised image
        sharpened_image = sharpen_image(denoised_image)

        # Convert the sharpened image back to RGB
        rgb_image = cv2.cvtColor(sharpened_image, cv2.COLOR_GRAY2RGB)

        # Compressed image filename with 'treated' prefix
        output_filename = "treated_" + os.path.splitext(os.path.basename(input_path))[0] + ".jpg"

        # Full path for the compressed image
        output_path = os.path.join(output_dir, output_filename)

        # Compress the sharpened image using Pillow
        img = Image.fromarray(rgb_image)
        img.save(output_path, 'JPEG', quality=quality)

if __name__ == "__main__":
    script_dir = os.path.dirname(os.path.abspath(__file__))
    output_dir = script_dir  # Save the treated image in the same folder as the script
    quality = 35  # Adjust the quality value as needed (0-100)

    # List all JPEG files in the script's directory
    jpeg_files = [f for f in os.listdir(script_dir) if f.lower().endswith('.jpg')]

    if not jpeg_files:
        print("No valid JPEG files found in the directory.")
    else:
        for jpeg_file in jpeg_files:
            input_path = os.path.join(script_dir, jpeg_file)
            process_image(input_path, output_dir, quality)

    input("Press any key to quit...")
