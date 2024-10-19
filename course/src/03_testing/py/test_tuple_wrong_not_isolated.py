import unittest
from unittest.mock import patch
from tupleguy import adjusted_values

class TestStatsModule(unittest.TestCase):

    # NOT MOCKING, LET US SEE WHAT HAPPENS
#    @patch('tupleguy.mean_std')
    # NO PATCH FUNCTION
    # NO PATCH ARGUMENT TO THE METHOD 
    # NO PATCH DEFINED INSIDE
    def test_adjusted_values(self):
        # Mock mean_std to return a fixed tuple of (mean, std_dev)

        # NOT MOCKING EXAMPLE AND RUN TO SEE WHAT HAPPENS
#        mock_mean_std.return_value = (10, 2)

        # Example data set
        data = [8, 10, 12]

        # Call the function that uses the mock
        result = adjusted_values(data)

        # Expected: [(8-10)/2, (10-10)/2, (12-10)/2] = [-1.0, 0.0, 1.0]
        expected = [-1.224, 0.0, +1.224]

        for r, e in zip(result, expected):
            self.assertAlmostEqual(r, e, places=2)# Assert the result matches the expected output

if __name__ == '__main__':
    unittest.main()

