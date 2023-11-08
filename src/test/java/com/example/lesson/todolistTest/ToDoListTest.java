package com.example.lesson.todolistTest;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@SpringBootTest
public class ToDoListTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void testTodoListAppSample(){
        page.navigate("http://localhost:8080/todosSample");
        String pageContent = page.content();
        Assertions.assertTrue(pageContent.contains("walk the dog"));
    }

    @Test
    void testTodoListTask(){
        page.navigate("http://localhost:8080/todos");
        assertThat(page
                .getByRole(AriaRole.LISTITEM))
                .hasText(new String[] {
                        "test drive application, use spring boot and playwright etc, unchecked",
                        "walk the dog, go outside with the dog, do not forget the dog, unchecked"
                });
    }
}
