import unittest
from unittest.mock import patch
from tupleguy import adjusted_values

class TestStatsModule(unittest.TestCase):

    @patch('tupleguy.mean_std')
    def test_adjusted_values(self, mock_mean_std):
        # Mock mean_std to return a fixed tuple of (mean, std_dev)
        mock_mean_std.return_value = (10, 2)

        # Example data set
        data = [8, 10, 12]

        # Call the function that uses the mock
        result = adjusted_values(data)

        # Expected: [(8-10)/2, (10-10)/2, (12-10)/2] = [-1.0, 0.0, 1.0]
        expected = [-1.0, 0.0, 1.0]

        # Assert the result matches the expected output
        self.assertEqual(result, expected)

if __name__ == '__main__':
    unittest.main()

