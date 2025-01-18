from visualiser.visualiser import Visualiser as vs

@vs(node_properties_kwargs={"shape": "record", "color": "#6aa84f", "style": "filled", "fillcolor": "lightgrey"})
def subsequences_after(partial_subsequence, word):
    """
    Recursively generates all subsequences of the given string, starting with the partial subsequence.
    
    Args:
        partial_subsequence (str): The accumulated subsequence so far.
        word (str): The remaining string to process.
    
    Returns:
        str: A comma-separated string of all subsequences.
    """
    # Base case
    if not word:
        return partial_subsequence

    # Recursive case
    without_word = subsequences_after(partial_subsequence, word[1:])
    with_word = subsequences_after(partial_subsequence + word[0], word[1:])

    # Combine results and return
    result = without_word + "," + with_word
    return result

def subsequences(word):
    return subsequences_after("", word)

# Testing the recursion and creating visualization
if __name__ == "__main__":
    print("Generating subsequences:")
    result = subsequences("abc")
    print("\nAll Subsequences:")
    print(result)
    
    # Generate a visualization of the recursion tree
    vs.make_animation("subsequences_visualization.gif", delay=2)

