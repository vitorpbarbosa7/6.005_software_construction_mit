import os
from pyhtml2pdf import converter

def convert_html_to_pdf(html_file):
    # Prepare the path for the HTML file to use in the converter
    path = os.path.abspath(html_file)
    pdf_file = html_file.replace('.html', '.pdf')
    try:
        # Convert the HTML file to PDF
        converter.convert(f'file:///{path}', pdf_file)
        print(f"Converted {html_file} to {pdf_file}")
    except Exception as e:
        print(f"Failed to convert {html_file}: {e}")

# Iterate over all files in the current directory
for file in os.listdir('.'):
    if file.endswith('.html'):
        convert_html_to_pdf(file)

