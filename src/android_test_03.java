@RunWith(Arquillian.class) @RunAsClient
public class MultipleAndroidContainersTest {

  @ArquillianResource
  AndroidDevice android;

  @Deployment(name = "android1")
  @TargetsContainer("android1")
  public static Archive<?> createArchive(){ ... }

  @Deployment(name = "android2")
  @TargetsContainer("android2")
  public static Archive<?> createArchive2(){ ... }

  @Test @OperateOnDeployment("android1")
  public void test01() {assertTrue(android!=null);}

  @Test @OperateOnDeployment("android2")
  public void test02() {assertTrue(android!=null);}
}
