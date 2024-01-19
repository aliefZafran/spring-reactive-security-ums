# Reactive Spring Security project
## Project Overview
This project is to demonstrate and experiment with Spring Reactive stack. It is a basic User Management System API which is developed in Spring Boot which consists of basic UMS feature such as:
- User Registration
- Email verification
- Forgot password
- Login

Since the focus was on exploring Spring Reactive stack, hence this project uses only basic authentication provided by Spring Security.

## Spring Reactive Stack
The Spring Reactive stack represents a paradigm shift in API development within the Spring ecosystem, moving from the traditional servlet stack. Utilizing the Spring Webflux framework, this approach introduces non-blocking concurrency handling, resulting in more efficient resource utilization.

## Challenges
Challenges while completing this project was the lack of resources that can be referred from the internet since this is a reactive project hence, there was a need to abstract general guides on developing reactive projects and devise tailored solutions. Additionally, encountering the lack of Object-Relational Mapping (ORM) tools supporting non-blocking operations posed a challenge. Fortunately, leveraging Spring Data R2DBC proved sufficient to overcome this obstacle.

## Technologies used
- Spring Security
- Spring Webflux
- Spring Data R2DBC
- Flyway
- Spring Mail

## Key Takeaways
1. **Fundamental understanding of Spring Security**: This project offered an invaluable opportunity to delve deeper into Spring Security, allowing for the creation of a robust and customized authentication and authorization system. Understanding the fundamentals of what Spring Security provides is powerful enough for one to develop a secured user management system.
2. **Reactive Programming Paradigm**: Embracing the reactive programming paradigm exposed me to a different coding approach. The reactive stack encourages a more declarative and expressive style, often using chain expressions. I found this a bit challenging as it requires one to know beforehand on which methods to choose from and how to use it properly
3. **Database Migration with Flyway**: The inclusion of Flyway for database migration was a pivotal learning experience. Utilizing Flyway streamlined the management of database versions, enabling seamless updates and migrations. This tool taught me on how valueable it is to have version controls for database especially when you are in a working team
4. **Adaptability and Resourcefulness**: The challenges faced during the development of this reactive project highlighted the importance of adaptability and resourcefulness. Navigating the scarcity of online resources necessitated abstracting general guides and formulating tailored solutions. This adaptive mindset proved instrumental in overcoming obstacles and highlights the significance of continuous learning in emerging technologies.
 
## Reflection
Reflecting on the journey, this project stands as more than a demonstration of technical skills. It is a testament to the multifaceted nature of learning—encompassing not only the mastery of specific tools and frameworks but also the development of problem-solving abilities and a resilient mindset in the face of challenges. The insights gained will undoubtedly be useful in the ever-evolving world of software development.