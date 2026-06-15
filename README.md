# Cake Shop System

A Java Swing desktop application focused on applying multiple object-oriented design patterns in one cake-ordering workflow. The project separates object creation, optional customization, shared system state, and order notifications into independent responsibilities.

## Features

- Choose between apple, chocolate, and cheesecake
- Customize orders with cream, Skittles, and chocolate chips
- Display order details through a desktop graphical interface
- Notify customer and manager dashboards when an order is completed
- Track cake sales by type

## Design Patterns

The main goal of the project is to demonstrate how several patterns can collaborate without placing the entire ordering process inside the GUI.

| Pattern | Main Role | Key Classes |
| --- | --- | --- |
| Singleton | Keeps one shared ordering service and order counter | `CakeOrderingSystem` |
| Simple Factory | Selects and creates the requested cake | `CakeFactory` |
| Abstract Factory | Creates a compatible family of cake ingredients | `CakeIngredientFactory` and its implementations |
| Decorator | Adds optional extras dynamically | `CakeDecorator`, `Cream`, `Skittles`, `ChocolateChips` |
| Observer | Sends completed-order updates to multiple displays | `OrderObserver`, `CustomerDashboard`, `ManagerDashboard` |

### 1. Singleton Pattern

**Purpose:** Ensure that the application uses exactly one shared `CakeOrderingSystem` instance.

The ordering system owns important shared state:

- The current order number
- The registered observers
- The cake factory used to create orders

Its constructor is private, so other classes cannot create it directly. The GUI obtains the shared instance through:

```java
CakeOrderingSystem system = CakeOrderingSystem.getInstance();
```

`getInstance()` uses double-checked locking:

```java
if (uniqueIns == null) {
    synchronized (CakeOrderingSystem.class) {
        if (uniqueIns == null) {
            uniqueIns = new CakeOrderingSystem();
        }
    }
}
```

The instance field is declared `volatile`, which makes the implementation safe when multiple threads try to access the singleton. This prevents separate windows or components from maintaining different order counters and observer lists.

**Participants:**

- Singleton class: `CakeOrderingSystem`
- Shared instance: `uniqueIns`
- Access method: `getInstance()`
- Client: `CakeShopGUI`

### 2. Simple Factory Pattern

**Purpose:** Move cake-selection and object-creation logic away from the GUI and ordering service.

`CakeFactory.createCake(type)` receives a cake type and returns the appropriate `Cake` object:

```java
Cake cake = cakeFactory.createCake(type);
```

The factory decides whether to create an `AppleCake`, `CheeseCake`, or `ChocolateCake`. The caller only works with the abstract `Cake` type and does not need to know the constructors of individual cake classes.

This reduces coupling between the ordering workflow and concrete cake implementations. Adding a new cake type mainly requires a new cake class, a matching ingredient factory, and one creation branch in `CakeFactory`.

> Note: This implementation is classified as a Simple Factory. It centralizes creation in one factory object rather than relying on subclasses to override a factory method.

**Participants:**

- Product abstraction: `Cake`
- Concrete products: `AppleCake`, `CheeseCake`, `ChocolateCake`
- Creator: `CakeFactory`
- Client: `CakeOrderingSystem`

### 3. Abstract Factory Pattern

**Purpose:** Create families of related ingredients that belong together without coupling a cake to concrete ingredient classes.

The `CakeIngredientFactory` interface defines the ingredient family:

```java
public interface CakeIngredientFactory {
    Dough createDough();
    filling createFilling();
    BaseTopping createBaseTopping();
}
```

Each concrete factory produces a compatible combination:

| Factory | Dough | Filling | Base Topping |
| --- | --- | --- | --- |
| `AppleCakeFactory` | `SpongeDough` | `AppleFilling` | `CinnamonDust` |
| `CheeseCakeFactory` | `BiscuitDough` | `CheeseCreamFilling` | `FruitGlaze` |
| `ChocolateCakeFactory` | `ChocolateDough` | `ChocolateCream` | `CocoaPowder` |

A concrete cake receives the factory through its constructor and asks it for ingredients during `prepare()`:

```java
dough = ingredientFactory.createDough();
filling = ingredientFactory.createFilling();
topping = ingredientFactory.createBaseTopping();
```

The cake depends on the `CakeIngredientFactory` abstraction rather than directly creating ingredients with `new`. As a result, ingredient families can change independently from the cake preparation workflow.

**Participants:**

- Abstract factory: `CakeIngredientFactory`
- Concrete factories: `AppleCakeFactory`, `CheeseCakeFactory`, `ChocolateCakeFactory`
- Abstract products: `Dough`, `filling`, `BaseTopping`
- Concrete products: ingredient implementation classes
- Clients: `AppleCake`, `CheeseCake`, `ChocolateCake`

### 4. Decorator Pattern

**Purpose:** Add optional extras to a cake at runtime without creating a separate subclass for every possible combination.

All decorators extend `CakeDecorator`, which itself extends `Cake`. Each decorator stores another `Cake` object:

```java
public abstract class CakeDecorator extends Cake {
    Cake cake;
}
```

When the customer selects extras, the order system wraps the cake:

```java
if (addCream) {
    cake = new Cream(cake);
}
if (addSkittles) {
    cake = new Skittles(cake);
}
if (addChocoChips) {
    cake = new ChocolateChips(cake);
}
```

For example, a chocolate cake with cream and chocolate chips becomes:

```text
ChocolateChips
`-- Cream
    `-- ChocolateCake
```

Calling `prepare()` starts at the outer decorator. Each decorator delegates to the wrapped cake before adding its own behavior. The decorators also extend `getName()` and `toString()` so the final order describes every selected extra.

Without this pattern, the system could require classes such as `ChocolateCakeWithCream`, `ChocolateCakeWithSkittles`, and `ChocolateCakeWithCreamAndSkittles`. The Decorator pattern avoids that subclass explosion and allows extras to be combined in any order.

**Participants:**

- Component: `Cake`
- Concrete components: the three base cake classes
- Base decorator: `CakeDecorator`
- Concrete decorators: `Cream`, `Skittles`, `ChocolateChips`
- Client that builds the chain: `CakeOrderingSystem`

### 5. Observer Pattern

**Purpose:** Notify multiple independent displays when an order is completed without hard-coding their behavior into the ordering system.

Observers implement one contract:

```java
public interface OrderObserver {
    void update(Cake cake, int orderNum);
}
```

The GUI creates and registers the displays:

```java
system.addObserver(managerDisplay);
system.addObserver(customerDisplay);
```

After an order is prepared, baked, cut, and boxed, the ordering system increments the order number and calls `notifyObservers()`. Every registered observer receives the same completed cake and order number.

The observers use that information differently:

- `CustomerDashboard` tells the customer that the order is ready.
- `ManagerDashboard` updates sales totals grouped by base cake type.

The subject knows only the `OrderObserver` interface. New observers, such as an inventory service or kitchen display, can be added without changing the existing notification loop.

**Participants:**

- Subject: `CakeOrderingSystem`
- Observer interface: `OrderObserver`
- Concrete observers: `CustomerDashboard`, `ManagerDashboard`
- Event data: completed `Cake` and order number

## Pattern Collaboration

The patterns form one complete order pipeline:

```text
CakeShopGUI
    |
    | gets shared instance
    v
CakeOrderingSystem (Singleton)
    |
    | requests a cake
    v
CakeFactory (Simple Factory)
    |
    | chooses an ingredient family
    v
CakeIngredientFactory (Abstract Factory)
    |
    | returns a prepared base cake
    v
Cake decorators (Decorator)
    |
    | order completed
    v
Registered dashboards (Observer)
```

1. The GUI accesses the shared ordering system through the Singleton.
2. The Simple Factory selects the concrete cake.
3. The Abstract Factory supplies a matching ingredient family.
4. Decorators wrap the cake with the customer's optional extras.
5. The completed order is published to every registered Observer.

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

## Author

Created as a Java object-oriented design patterns project.
