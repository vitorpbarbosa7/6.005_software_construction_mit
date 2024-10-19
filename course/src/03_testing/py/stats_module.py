# stats_module.py

def calculate_mean(data):
    """
    Calculate the mean of a list of numbers.
    """
    if not data:
        return 0
    return sum(data) / len(data)


def calculate_standard_deviation(data):
    """
    Calculate the standard deviation of a list of numbers.
    """
    mean = calculate_mean(data)
    breakpoint()
    variance = sum((x - mean) ** 2 for x in data) / len(data)
    return variance ** 0.5

