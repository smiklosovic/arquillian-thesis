@RunWith(Arquillian.class) @RunAsClient
public class AndroidContainerTest {

  @ArquillianResource
  AndroidDevice android;

  @Deployment(name = "android1")
  public static Archive<?> createArchive() {...}

  @Test @OperateOnDeployment("android1")
  public void test01() {
    assertTrue(android != null);
  }
}
