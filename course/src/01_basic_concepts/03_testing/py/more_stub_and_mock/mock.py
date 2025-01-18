from unittest.mock import Mock

# Define the mock
mock_db = Mock()

# Define what the mock should return when a method is called
mock_db.get_user_data.return_value = {"name": "John Doe", "age": 30}

# Test the function using the mock
def test_get_user_info():
    result = mock_db.get_user_data(1)
    assert result["name"] == "John Doe"
    assert result["age"] == 30

    # this extra assert    
    # Assert that the get_user_data method was called with the argument 1
    mock_db.get_user_data.assert_called_once_with(1)
