@RunWith(Arquillian.class) @RunAsClient
public class ContainerTest {
  @Deployment(name = "android", testable = false)
  @TargetsContainer("android")
  public static Archive<?> createDeployment() {
    /* return APK */
  }
  @Test
  @OperateOnDeployment("android")
  public void test01(@ArquillianResource AndroidDevice android,
        @Drone WebDriver driver) { // tests
  }}
