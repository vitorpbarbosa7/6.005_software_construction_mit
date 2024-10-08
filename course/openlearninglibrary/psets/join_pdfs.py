import PyPDF2
import os
from natsort import natsorted  # Import natsorted for natural sorting

def merge_pdfs(output_filename, input_filenames):
    pdf_merger = PyPDF2.PdfMerger()

    for pdf_file in input_filenames:
        pdf_merger.append(pdf_file)

    with open(output_filename, "wb") as output_file:
        pdf_merger.write(output_file)

# Example usage:
pdf_files = natsorted([file for file in os.listdir() if ".pdf" in file])  # Use natsorted for natural sorting

output_pdf = "merged_output.pdf"  # Output PDF file name
merge_pdfs(output_pdf, pdf_files)

