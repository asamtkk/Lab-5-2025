import functions.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Тестирование методов ArrayTabulatedFunction и LinkedListTabulatedFunction ===\n");

        //создаем тестовые точки
        FunctionPoint[] points = {
                new FunctionPoint(0.0, 1.0),
                new FunctionPoint(1.0, 3.0),
                new FunctionPoint(2.0, 5.0),
                new FunctionPoint(3.0, 7.0)
        };

        //создаем функции
        ArrayTabulatedFunction arrayFunc = new ArrayTabulatedFunction(points);
        LinkedListTabulatedFunction listFunc = new LinkedListTabulatedFunction(points);

        //1. тестирование toString()
        System.out.println("1. Метод toString():");
        System.out.println("ArrayTabulatedFunction: " + arrayFunc.toString());
        System.out.println("LinkedListTabulatedFunction: " + listFunc.toString());
        System.out.println();

        //2. тестирование equals()
        System.out.println("2. Метод equals():");

        //создаем одинаковые функции
        ArrayTabulatedFunction arrayFunc2 = new ArrayTabulatedFunction(points);
        LinkedListTabulatedFunction listFunc2 = new LinkedListTabulatedFunction(points);

        //создаем разные функции
        FunctionPoint[] differentPoints = {
                new FunctionPoint(0.0, 1.0),
                new FunctionPoint(1.0, 2.0), // другое значение Y
                new FunctionPoint(2.0, 5.0)
        };
        ArrayTabulatedFunction differentArrayFunc = new ArrayTabulatedFunction(differentPoints);

        System.out.println("arrayFunc.equals(arrayFunc2): " + arrayFunc.equals(arrayFunc2));
        System.out.println("listFunc.equals(listFunc2): " + listFunc.equals(listFunc2));
        System.out.println("arrayFunc.equals(listFunc): " + arrayFunc.equals(listFunc));
        System.out.println("arrayFunc.equals(differentArrayFunc): " + arrayFunc.equals(differentArrayFunc));
        System.out.println();

        //3. тестирование hashCode()
        System.out.println("3. Метод hashCode():");
        System.out.println("arrayFunc.hashCode(): " + arrayFunc.hashCode());
        System.out.println("arrayFunc2.hashCode(): " + arrayFunc2.hashCode());
        System.out.println("listFunc.hashCode(): " + listFunc.hashCode());
        System.out.println("listFunc2.hashCode(): " + listFunc2.hashCode());
        System.out.println("differentArrayFunc.hashCode(): " + differentArrayFunc.hashCode());

        //проверяем согласованность equals и hashCode
        System.out.println("arrayFunc.equals(arrayFunc2) && arrayFunc.hashCode() == arrayFunc2.hashCode(): " +
                (arrayFunc.equals(arrayFunc2) && arrayFunc.hashCode() == arrayFunc2.hashCode()));
        System.out.println();

        //тестирование изменения объекта и хэш-кода
        System.out.println("4. Изменение объекта и хэш-кода:");
        System.out.println("Исходный hashCode arrayFunc: " + arrayFunc.hashCode());
        try {
            arrayFunc.setPointY(1, 3.001); // незначительное изменение
            System.out.println("После изменения Y[1] на 3.001: " + arrayFunc.hashCode());
        } catch (Exception e) {
            System.out.println("Ошибка при изменении: " + e.getMessage());
        }
        System.out.println();

        // 5.тестирование clone()
        System.out.println("5. Метод clone():");

        ArrayTabulatedFunction arrayClone = (ArrayTabulatedFunction) arrayFunc.clone();
        LinkedListTabulatedFunction listClone = (LinkedListTabulatedFunction) listFunc.clone();

        System.out.println("arrayFunc == arrayClone: " + (arrayFunc == arrayClone));
        System.out.println("arrayFunc.equals(arrayClone): " + arrayFunc.equals(arrayClone));
        System.out.println("listFunc == listClone: " + (listFunc == listClone));
        System.out.println("listFunc.equals(listClone): " + listFunc.equals(listClone));

        //проверка глубокого клонирования
        System.out.println("\n6. Проверка глубокого клонирования:");
        try {
            //изменяем оригинал
            arrayFunc.setPointY(0, 100.0);
            listFunc.setPointY(0, 200.0);

            System.out.println("После изменения оригиналов:");
            System.out.println("arrayFunc.getPointY(0): " + arrayFunc.getPointY(0));
            System.out.println("arrayClone.getPointY(0): " + arrayClone.getPointY(0));
            System.out.println("listFunc.getPointY(0): " + listFunc.getPointY(0));
            System.out.println("listClone.getPointY(0): " + listClone.getPointY(0));

            System.out.println("Клоны не изменились: " +
                    (arrayClone.getPointY(0) != 100.0 && listClone.getPointY(0) != 200.0));

        } catch (Exception e) {
            System.out.println("Ошибка при проверке клонирования: " + e.getMessage());
        }
    }
}