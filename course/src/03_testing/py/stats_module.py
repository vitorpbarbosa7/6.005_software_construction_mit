# stats_module.py

def calculate_mean(data):
    """Calculate the mean of a list of numbers."""
    return sum(data) / len(data)

def calculate_variance(data, mean):
    """Calculate the variance of a list of numbers."""
    return sum((x - mean) ** 2 for x in data) / len(data)

def calculate_standard_deviation(data):
    """Calculate the standard deviation of a list of numbers."""
    mean = calculate_mean(data)
    variance = calculate_variance(data, mean)
#    breakpoint()
    return variance ** 0.5

