# cooking-lab-recipes-api

This API aims to build cooking-lab's own recipes instead of using external APIs.

## Installation 🛠️

To install this project, follow the steps below:

1. Clone the project:
    ```sh
    git clone https://github.com/CookingLab/cooking-lab-recipes-api.git
    ```

2. Navigate to the project directory:
    ```sh
    cd cooking-lab-recipes-api
    ```

3. Install the dependencies:
    ```sh
    ./mvnw install
    ```
   If you get **./mvnw: Permission denied** error, run the following command line:
   ```sh
   chmod +x ./mvnw
   ./mvnw install
   ```

4. Run the application:
    ```sh
    ./mvnw spring-boot:run
