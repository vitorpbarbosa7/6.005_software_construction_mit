# test_stats_module.py
import unittest
from unittest.mock import patch
from stats_module import calculate_standard_deviation

class TestStatsModule(unittest.TestCase):
    
    # get the 
    @patch('stats_module.calculate_mean')
    def test_calculate_standard_deviation(self, mock_mean):
        # Mock the mean function to return a fixed value
        mock_mean.return_value = 10

        # Test the standard deviation function using the mock
        data = [8, 10, 12]
        # inside the calculate_standard_deviation it will use the patched
        result = calculate_standard_deviation(data)
        breakpoint()

        # Expecting the standard deviation based on the mocked mean of 10
        expected_variance = sum((x - 10) ** 2 for x in data) / len(data)
        expected_stddev = expected_variance ** 0.5
        self.assertAlmostEqual(result, expected_stddev)

    def test_calculate_standard_deviation_without_mock(self):
        # Test without mocking to check the actual calculation
        data = [1, 2, 3, 4, 5]
        result = calculate_standard_deviation(data)
        # Calculate expected standard deviation
        # not mocking the mean, but really calculating
        # so that the function imported is not necessary
        expected_mean = sum(data) / len(data)
        expected_variance = sum((x - expected_mean) ** 2 for x in data) / len(data)
        expected_stddev = expected_variance ** 0.5
        self.assertAlmostEqual(result, expected_stddev)

if __name__ == '__main__':
    unittest.main()

