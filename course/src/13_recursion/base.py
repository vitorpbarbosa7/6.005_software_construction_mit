def stringValue(n, base):

    quotient = n//base
    remainder = n%base
    
    # base case
    if quotient == 0:
        return str(remainder)
    
    # relate subproblems
    current_level = str(remainder)
    deeper_level = stringValue(quotient, base)

    return deeper_level + current_level
    


n = 16
print(stringValue(n, 10))
print(stringValue(n, 2))
