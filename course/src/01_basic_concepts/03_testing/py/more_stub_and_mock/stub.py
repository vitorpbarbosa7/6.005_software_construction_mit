class Database:
    def get_user_data(self, user_id):
        # In real code, it would query a database
        pass

class TestDatabase:
    def get_user_data(self, user_id):
        return {"name": "John Doe", "age": 30}  # Predefined value (stubbed response)

# Test the function using the stub
def test_get_user_info():
    db = TestDatabase()  # Use the stub instead of the real database
    result = db.get_user_data(1)
    assert result["name"] == "John Doe"
    assert result["age"] == 30
