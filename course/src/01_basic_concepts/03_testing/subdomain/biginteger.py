class BigInteger:

    def __init__(self, num):
        self._num = num

    def multiply(self, other):
        return self._num*other._num

a = BigInteger(2)
b = BigInteger(3)

# self
# this
c = a.multiply(b)

print(c)
