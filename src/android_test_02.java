@RunWith(Arquillian.class) @RunAsClient
public class MixedContainersTest {

  @Deployment(name = "android1")
  @TargetsContainer("android1")
  public static Archive<?> createDeployment1() {...}

  @Deployment(name = "jbossas")
  @TargetsContainer("jbossas")
  public static Archive<?> createDeployment2() {...}

  @Test @InSequence(1)
  @OperateOnDeployment("android1")
  public void test01(
      @ArquillianResource AndroidDevice android) {
      Assert.assertTrue(android != null);
  }

  @Test @InSequence(2)
  @OperateOnDeployment("jbossas")
  public void test02() {Assert.assertTrue(true);}
}
