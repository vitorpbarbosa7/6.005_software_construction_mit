def helper(n, base):

    quotient = n//base
    remainder = n%base
    
    # base case
    if quotient == 0:
        return str(remainder)
    
    # relate subproblems
    current_level = str(remainder)
    deeper_level = helper(quotient, base)

    return deeper_level + current_level
    

def stringValue(n, base):

    sign = "";

    if n < 0:
        n = -n;
        sign = "-"

    return sign + helper(n, base)

n = 16
print(stringValue(n, 10))
print(stringValue(-n, 10))
print(stringValue(n, 2))
print(stringValue(-n, 2))
