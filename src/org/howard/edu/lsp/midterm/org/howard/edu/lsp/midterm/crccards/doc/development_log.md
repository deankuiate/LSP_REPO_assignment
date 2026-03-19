# Development Log – Question 1

## External Resources Used

### AI Tool: Claude (Anthropic)

**Complete Conversation Transcript:**

**Prompt:** I have a Java midterm exam. Question 1 asks me to implement two classes — Task and TaskManager — based on CRC cards for a Task Management System. The Task class needs fields taskId, description, and status with a default status of "OPEN". It needs getters, a setStatus method that sets status to "UNKNOWN" for invalid values, and a toString() method. TaskManager needs to store tasks in a data structure that prevents duplicates, supports lookup by ID, and can return tasks filtered by status. The methods are addTask (throws IllegalArgumentException on duplicate), findTask (returns null if not found), and getTasksByStatus. The solution must work with a provided Driver class.

**Response:** Claude provided complete implementations of Task.java and TaskManager.java with Javadoc comments. For the Task class, it implemented the constructor, getters, setStatus with validation logic, and toString() in the required format. For TaskManager, it recommended using a LinkedHashMap<String, Task> to store tasks by taskId, which supports O(1) lookup, prevents duplicate IDs by key, and preserves insertion order. It implemented addTask with the duplicate check, findTask returning null when not found, and getTasksByStatus iterating over values and filtering by status.
