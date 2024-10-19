def mean_std(data):
    """Calculate the mean and standard deviation of a list of numbers."""
    mean = sum(data) / len(data)
    variance = sum((x - mean) ** 2 for x in data) / len(data)
    std_dev = variance ** 0.5
    return mean, std_dev

def adjusted_values(data):
    """Example function that uses mean_std."""
    mean, std_dev = mean_std(data)
    # Adjust values by subtracting the mean and dividing by std deviation
    return [(x - mean) / std_dev for x in data]

