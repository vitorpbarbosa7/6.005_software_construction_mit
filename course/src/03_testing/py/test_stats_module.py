# test_stats_module.py

import unittest
# patching, so temporarily altering the behaviour of the function, to have this mocked function
# not the real one
from unittest.mock import patch
from stats_module import calculate_standard_deviation

class TestStatsModule(unittest.TestCase):

    @patch('stats_module.calculate_mean')
    @patch('stats_module.calculate_variance')
    def test_calculate_standard_deviation(self, mock_variance, mock_mean):
        # Mock the mean and variance functions to return fixed values
        mock_mean.return_value = 10
        mock_variance.return_value = 4

        # Test the standard deviation function using the mocks
        data = [8, 10, 12]
        result = calculate_standard_deviation(data)

        # Expected standard deviation based on the mocked variance of 4
        expected_stddev = 2  # √4
        self.assertAlmostEqual(result, expected_stddev)

if __name__ == "__main__":
    unittest.main()

