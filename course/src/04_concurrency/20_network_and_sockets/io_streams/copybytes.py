# Python equivalent of the Java CopyBytes program

def copy_bytes():
    # Open the input and output files
    try:
        with open("xanadu.txt", "rb") as in_file, open("outagain.txt", "wb") as out_file:
            # Read and write byte-by-byte
            while (byte := in_file.read(1)):
                out_file.write(byte)
    except IOError as e:
        print(f"Error: {e}")

# Call the function to copy bytes
copy_bytes()

