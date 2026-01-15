import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.LoginPage;
import utils.TestUtils;
import utils.UserManager;
import model.User;

public class LoginFromDifferentPlacesTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private User testUser;

    @Before
    public void setUp() {
        // Каждый тест создает своего пользователя через API
        testUser = UserManager.createTestUser();
        System.out.println("📧 Создан тестовый пользователь: " + testUser.getEmail());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        // Удаляем тестового пользователя после каждого теста
        UserManager.deleteTestUser(testUser);
        System.out.println("🧹 Тестовый пользователь удален");
    }

    @Test
    @DisplayName("1. Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка успешного входа по кнопке «Войти в аккаунт» на главной")
    public void loginFromMainPageButtonTest() {
        driver = TestUtils.createDriver();
        loginPage = new LoginPage(driver);

        // Переход на главную страницу через Page Object
        loginPage.goToMainPage();

        // Нажимаем кнопку "Войти в аккаунт" через Page Object
        loginPage.clickOnEnterAccountButton();

        // Логин через Page Object
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // Полная проверка авторизации
        boolean isFullyAuthorized = loginPage.fullAuthorizationCheck(testUser.getEmail());

        Assert.assertTrue("Пользователь должен быть авторизован с правильным email",
                isFullyAuthorized);
    }

    @Test
    @DisplayName("2. Вход через кнопку «Личный кабинет»")
    @Description("Проверка успешного входа по кнопке «Личный кабинет»")
    public void loginFromPersonalAccountButtonTest() {
        driver = TestUtils.createDriver();
        loginPage = new LoginPage(driver);

        // Переход на главную страницу через Page Object
        loginPage.goToMainPage();

        // Нажимаем кнопку "Личный Кабинет" через Page Object
        loginPage.clickOnPersonalAccountLink();

        // Логин через Page Object
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // Полная проверка авторизации
        boolean isFullyAuthorized = loginPage.fullAuthorizationCheck(testUser.getEmail());

        Assert.assertTrue("Пользователь должен быть авторизован с правильным email",
                isFullyAuthorized);
    }

    @Test
    @DisplayName("3. Вход через кнопку в форме регистрации")
    @Description("Проверка успешного входа по кнопке в форме регистрации")
    public void loginFromRegistrationFormButtonTest() {
        driver = TestUtils.createDriver();
        loginPage = new LoginPage(driver);

        // Переход на страницу регистрации через Page Object
        loginPage.goToRegisterPage();

        // Нажимаем на ссылку "Войти" через Page Object
        loginPage.clickOnRegisterLink();

        // Логин через Page Object
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // Полная проверка авторизации
        boolean isFullyAuthorized = loginPage.fullAuthorizationCheck(testUser.getEmail());

        Assert.assertTrue("Пользователь должен быть авторизован с правильным email",
                isFullyAuthorized);
    }

    @Test
    @DisplayName("4. Вход через кнопку в форме восстановления пароля")
    @Description("Проверка успешного входа по кнопке в форме восстановления пароля")
    public void loginFromPasswordRecoveryButtonTest() {
        driver = TestUtils.createDriver();
        loginPage = new LoginPage(driver);

        // Переход на страницу восстановления пароля через Page Object
        loginPage.goToForgotPasswordPage();

        // Нажимаем на ссылку "Войти" через Page Object
        loginPage.clickOnForgotPasswordLink();

        // Логин через Page Object
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // Полная проверка авторизации
        boolean isFullyAuthorized = loginPage.fullAuthorizationCheck(testUser.getEmail());

        Assert.assertTrue("Пользователь должен быть авторизован с правильным email",
                isFullyAuthorized);
    }
}
