#!/bin/bash

# Define server host and port
SERVER="localhost"
PORT="4444"

# Function to send a sequence of commands using telnet
send_commands() {
    (
        echo "dig 0 1"
        sleep 0.01
        echo "dig 0 2"
        sleep 0.01
        echo "dig 1 0"
        sleep 0.01
        echo "flag 3 3"
        sleep 0.01
        echo "flag 0 0"
        sleep 0.01
        echo "dig 2 1"
        sleep 0.01
        echo "dig 3 2"
        sleep 0.01
        echo "flag 3 2"
        sleep 0.01
    ) | telnet $SERVER $PORT
}

# Run multiple clients in parallel
send_commands &
send_commands &
wait

