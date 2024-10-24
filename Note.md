# Access Modifiers

## Public
- Có thể truy cập từ bất kỳ đâu
### Example
Chúng ta có một class `Animal` với property `name` và method `eat` được khai báo với access modifier là `public`
Ngoài ra method `eat` có thể được truy cập từ bất kỳ đâu
```java
public class Animal {
    public String name;
    public void eat() {
        System.out.println("Eating");
    }
}
```

Bây giờ chúng ta có thể truy cập property `name` và method `eat` từ bất kỳ đâu
```java
public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.name = "Dog";
        animal.eat();
    }
}
```

## Protected
- Chỉ có thể truy cập từ class cha hoặc class con
- Có thể truy cập từ cùng package
- Có thể truy cập từ class con ở package khác

### Example
- Package: `com.example`
- Class: `Animal`
```java
package com.example;

public class Animal {
    protected String name;
    protected void eat() {
        System.out.println("Eating");
    }
}
```

- Package: `com.example`
- Class: `Dog`
```java
package com.example;

public class Dog extends Animal {
    public void display() {
        System.out.println(name);
        eat();
    }
}
```

- Package: `com.example`
- Class: `Main`
```java
package com.example;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.name = "Dog";
        dog.eat();
    }
}
```

- Package: `com.example2`
- Class: `Main`
```java
package com.example2;

import com.example.Dog;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.name = "Dog"; // Error
        dog.eat(); // Error
    }
}
```

## Private
- Chỉ có thể truy cập từ bên trong class đó

### Example
Chúng ta có một class `Animal` với property `name` và method `eat` được khai báo với access modifier là `private`
Ngoài ra method `eat` chỉ có thể được truy cập từ bên trong class `Animal`
```java
public class Animal {
    private String name;
    private void eat() {
        System.out.println("Eating");
    }
}
```

Bây giờ chúng ta không thể truy cập property `name` và method `eat` từ bất kỳ đâu
```java
public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.name = "Dog"; // Error
        animal.eat(); // Error
    }
}
```

Hoặc khi kế thừa từ class `Animal` thì cũng không thể truy cập property `name` và method `eat`
```java
public class Dog extends Animal {
    public void display() {
        System.out.println(name); // Error
        eat(); // Error
    }
}
```
