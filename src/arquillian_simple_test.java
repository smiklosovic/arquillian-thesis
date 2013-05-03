@RunWith(Arquillian.class)
public class MyTestCase {
    @Deployment("A") @TargetsContainer("jbossas-A")
    public static Archive<?> createDeployment1() {
        return ShrinkWrap.create(JavaArchive.class)
            .add(SomeClass.class);
    }

    @Test @OperatesOnDeployment("A")
    public void testOnAAtJBossA() {}
}
