import socket
import threading
import time

# Define a function for handling client communication
def client_handler(client_socket, client_name):
    try:
        # Send a greeting to the client
        client_socket.send(f"Hello {client_name}! You are connected.".encode())

        # Start receiving messages from the client for 10 seconds
        start_time = time.time()
        while time.time() - start_time < 10:
            message = client_socket.recv(1024).decode()  # Receive message from client
            if message:
                print(f"{client_name} says: {message}")
                client_socket.send(f"Received: {message}".encode())  # Echo the message back
            else:
                break

        print(f"{client_name} disconnected.")
    except Exception as e:
        print(f"Error with {client_name}: {e}")
    finally:
        client_socket.close()

# Server function
def server():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind(('127.0.0.1', 8080))  # Bind to port 8080
    server_socket.listen(5)  # Listen for connections (maximum of 5 clients)
    print("Server is listening on port 8080...")

    # Accept connections from clients
    while True:
        client_socket, client_address = server_socket.accept()
        print(f"Client {client_address} connected!")
        
        # Start a new thread to handle the client communication
        client_name = f"Client {client_address}"
        threading.Thread(target=client_handler, args=(client_socket, client_name)).start()

# Client function
def client():
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect(('127.0.0.1', 8080))  # Connect to the server on localhost and port 8080

    # Receive the greeting message from the server
    server_message = client_socket.recv(1024).decode()
    print(f"Server: {server_message}")

    # Send some messages to the server
    for i in range(5):
        message = f"Hello from client! Message #{i + 1}"
        client_socket.send(message.encode())
        server_response = client_socket.recv(1024).decode()
        print(f"Server responds: {server_response}")
        time.sleep(1)  # Wait for 1 second before sending the next message

    # Close the connection
    client_socket.send("Goodbye!".encode())
    client_socket.close()
    print("Disconnected from server.")

if __name__ == "__main__":
    # Run server in a separate thread
    threading.Thread(target=server, daemon=True).start()

    # Run two client instances (simulating them as separate clients)
    time.sleep(1)  # Ensure server is up before clients start
    threading.Thread(target=client).start()
    threading.Thread(target=client).start()

    # Run the main thread for 10 seconds before stopping
    time.sleep(10)

