# BankBank Inc. API Documentation

Welcome to the official documentation for the BankBank Inc. API. BankBank is an innovative virtual bank that aims to provide seamless financial services through modern technology. This API allows developers to interact with BankBank's backend system to perform various banking operations programmatically.

## Technologies Used

- **Java Spring Boot**: BankBank's backend is built using Spring Boot, a powerful framework for building Java-based applications.
- **PostgreSQL**: We utilize PostgreSQL, a robust open-source relational database, to store and manage our data securely.
- **Gradle**: Gradle is a tool for automating the build process of software projects. It helps manage dependencies and tasks efficiently.
- **Google Java Format**: Google Java Format automatically formats Java code according to Google's style guidelines, ensuring consistency and readability.
- **React / Next.js**: For the web interface, we leverage React and Next.js to create a dynamic and intuitive user experience. [Available in Github](https://github.com/strawberries-team/bankbank-ui).

## Getting Started

To start using BankBank's API, follow these steps:

1. **Clone the Repository**: Clone the GitHub repository for BankBank's API to your local machine.

   ```bash
   git clone https://github.com/strawberries-team/bankbank-api.git
   ```

2. **Set Up PostgreSQL**: Ensure you have PostgreSQL installed on your machine. Run the database migrations for BankBank's API and set your variables environments for the database connection. 

3. **Build and Run the Backend**:

   ```bash
   cd bankbank-api
   ./gradlew bootRun
   ```

   This command will compile the Java code, start the Spring Boot application, and deploy the API locally.

4. **Set Up the Web Interface**:

    Use the React/Next.js development server. [Available in Github](https://github.com/strawberries-team/bankbank-ui).

5. **Access BankBank's API**:

   Once the backend and frontend servers are up and running, you can access BankBank's API through the provided endpoints.

## API Endpoints

For detailed information about each endpoint and the expected request and response formats
- [View it on SwaggerUI](https://petstore.swagger.io/?url=https://raw.githubusercontent.com/strawberries-team/bankbank-api/prod/docs/openapi.yaml#)
- [View it on Postman](https://www.postman.com/lunar-module-observer-68161093/workspace/bankbank-workspace/collection/28551443-a93df2b2-6a58-48a5-a888-53f7ba278f29?action=share&creator=28551443)

## Conceptual Database Model

![Bank Bank - Conceptual Data Model](./docs/db/bankbank-conceptual-diagram.svg)

## Logical Database Model

![Bank Bank - Logical Data Model](./docs/db/bankbank-logical-diagram.svg)

## Contributing

We welcome contributions from the community to improve BankBank's API. If you find any issues or have suggestions for enhancements, please submit a pull request or open an issue on GitHub.

## Support

If you encounter any problems or have questions about using BankBank's API, feel free to reach out to our team.

Salohy ZERAMBAY - [Salohyy212](https://github.com/Salohyy212)

Andy RATOETRARIVO - [AndyPendragon](https://github.com/AndyPendragon)

Mialitina RANDRIANARIJAONA - [itsmiaetzzz](https://github.com/itsmiaetzzz)

---

Thank you for choosing BankBank Inc. for your virtual banking needs! We're excited to have you on board.
