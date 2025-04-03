package ru.netology.servise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldSearchTasksByQuery() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(555, "Выкатка версии", "НетоБанк", "Вторник");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] result = todos.search("НетоБанк");

        Task[] expected = {meeting};
        Assertions.assertArrayEquals(expected, result);
    }
    @Test
    public void shouldFindMultipleTasks() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить в банк");
        String[] subtasks = {"Купить молоко", "Позвонить в банк", "Сделать домашку"};
        Epic epic = new Epic(2, subtasks);
        Meeting meeting = new Meeting(3, "Обсуждение кредита", "Банк ВТБ", "Среда 15:00");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic};
        Task[] actual = todos.search("Позвонить в банк");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindExactlyOneTask() {
        SimpleTask simpleTask = new SimpleTask(1, "Сходить в магазин");
        String[] subtasks = {"Купить молоко", "Купить хлеб"};
        Epic epic = new Epic(2, subtasks);
        Meeting meeting = new Meeting(3, "Встреча с друзьями", "День рождения", "Пятница 19:00");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("День рождения");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindZeroTasks() {
        SimpleTask simpleTask = new SimpleTask(1, "Прочитать книгу");
        String[] subtasks = {"Посмотреть фильм", "Сходить в спортзал"};
        Epic epic = new Epic(2, subtasks);
        Meeting meeting = new Meeting(3, "Рабочее совещание", "Проект X", "Понедельник 10:00");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("Путешествие");

        Assertions.assertArrayEquals(expected, actual);
    }
}
