import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.Archive;

Archive<?> jar = ShrinkWrap.create(
    JavaArchive.class, "test.jar")
  .addPackage("net.miklosovic.thesis.ejb.dao")
  .addPackage("net.miklosovic.thesis.ejb.model")
  .addClass(CustomerService.class)
  .addClass(Customer.class)
  .addAsResource("test-persistence.xml",
    "META-INF/persistence.xml")
  .addAsManifestResource(EmptyAsset.INSTANCE,
    "beans.xml");
