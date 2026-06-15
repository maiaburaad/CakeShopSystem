# Cake Shop System

A desktop cake-ordering application built with Java Swing. The project demonstrates how multiple object-oriented design patterns can work together in a simple ordering workflow.

## Features

- Choose between apple, chocolate, and cheesecake
- Customize orders with cream, Skittles, and chocolate chips
- Display order details through a desktop graphical interface
- Notify customer and manager dashboards when an order is completed
- Track cake sales by type

## Design Patterns

| Pattern | Usage |
| --- | --- |
| Singleton | Maintains one shared `CakeOrderingSystem` instance |
| Factory Method | Creates the selected cake type |
| Abstract Factory | Creates each cake's dough, filling, and topping |
| Decorator | Adds optional extras without changing the base cake classes |
| Observer | Notifies customer and manager dashboards about completed orders |

## Technologies

- Java 19
- Java Swing
- Apache Maven
- NetBeans

## Project Structure

```text
src/main/java/com/mycompany/cakeshopsystem/
|-- CakeShopSystem.java       # Application entry point
|-- CakeShopGUI.java          # Swing user interface
|-- CakeOrderingSystem.java   # Ordering workflow
|-- CakeFactory.java          # Cake creation
|-- CakeDecorator.java        # Base decorator
`-- OrderObserver.java        # Observer contract
```

## Getting Started

### Prerequisites

- JDK 19 or later
- Apache Maven 3.8 or later

### Run the Application

```bash
mvn clean compile exec:java
```

You can also open the project in NetBeans and run `CakeShopSystem.java`.

## How It Works

1. The user selects a cake type and optional extras.
2. The factory creates the cake and its matching ingredients.
3. Decorators wrap the cake with the selected extras.
4. The system prepares and completes the order.
5. Registered observers receive the completed-order notification.

## Author

Created as a Java object-oriented programming project.
