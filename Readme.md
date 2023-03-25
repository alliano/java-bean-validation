# Pengenalan Bean Validation
Bean validation merupakan standar spesifikasi untuk melakukan vaidasi pada pemograman java.
Bean validation sendiri merupakan spesifikasi yang masuk kedalam teknologi Java Enterprise yang sekarang sudah di ubah namanya menjadi Jakarta Enterprise.
Dengan bean validation, kita bisa membuat kode validation yang sudah terstandarisasi pada bahasa pemograman java.   
reference : https://jakarta.ee/spesifications/bean-validation

# Hibernate Validator
Bean validator hanyalah spesifikasi standard, isinya hanyalah kumpulan kontrak interface dan Annotatiois, mirip seperti JBBC(Java Database Connectivity).
Untuk implementasinya, kita butuh Driver untuk bean validation.
Hibernate validation adalah salahsatu driver atau implementasi Bean Validation yang paling populer saat ini.   
reference : https://hibernate.org/validator   

Diagram sebelum menggunakan Bean Validator  
![without bean validation](https://github.com/alliano/java-bean-validation/blob/master/src/main/resources/imgs/withoutBeanValdation.jpg)   
Jika kita tidak menggunakan Bean Validation, maka kita akan membuat Bean Validation di setiap layer dan itu kurang baik karna kita menulis kode yang sama di setiap layer.  

Diagram setelah menggunakan Bean Validation  
![with bean validation](https://github.com/alliano/java-bean-validation/blob/master/src/main/resources/imgs/withBeanValidation.jpg)
Setelah menggunakan Bean Validation kita akan membuat Validasi nya terpusat, jadi layer-layer lain seprrti Presentation Layer, Bussines Layer, dan Data Access layer tidak akan melakuakn custom validasi secara rendudan.

# Jakarta Bean Validation
Saat ini varsi terbaru dari Bean Validation adalah versi 3. Bean Validation versi 2 masih menggunakan nama Java Enterprise Edition, sedangkan Bean Validation versi 3 megubah namanya menjadi Jakarta Enterprise Edition.
Oleh karna intu saat ini, banyak package untuk Bean Validation yang sudah berubah, dari yang sebelumnya menggunakan package javax.validation menjadi jakarta.validation.

# Installation
Untuk menggunakan Bean Validation ada beberapa dependency yang harus ditambahakan pada pom.xml pada project kita, Berikut ini adalaha dependency yang kita butuhkan :
Bean Validation
``` xml
<dependency>
	<groupId>jakarta.validation</groupId>
	<artifactId>jakarta.validation-api</artifactId>
	<version>3.0.2</version>
</dependency>
```
Hibernate Validator
``` xml
<dependency>
	<groupId>org.hibernate.validator</groupId>
	<artifactId>hibernate-validator</artifactId>
	<version>8.0.0.Final</version>
</dependency>
```
Jakarta-el
``` xml
<dependency>
	<groupId>org.glassfish</groupId>
	<artifactId>jakarta.el</artifactId>
	<version>4.0.2</version>
</dependency>
```
Ketiga dependency tersebut harus ditambahkan, jakarta-validation unutk kontrak interface nya dan Hibernate Validator itu adalah untuk implementasi dari jakarta-validatornya.

# Validator
validator adalah class utama dalam Bean Validator.
Validator digunakan sebagai object untuk mengeksekusi validation.
Validtor itu adalah Object yang sangat besar dan tentunya itu berat, Oleh karna itu sebaiknya Object Validator dibuat hanya 1 kali saja dalam applikasi kita nanti.  
Reference : https://jakarta.ee/spesification/bean-validation/3.0/apidocs/jakarta/validation/validator

# ValidationFactory
Validation merupakan interface, untuk membuat object tersebut kita membutuhkan bantuan object ValidationFactory.
ValidationFactory merupakan sebuah interface yang digunakan untuk membuat object-object yang ada pada Bean Validation.
ValidatorFacotory merupakan interface yang cukup berat, langkah baiknya nanti kita buat objectnya 1 kali saja dalam applikasi kita, Untk membuat object ValidatorFacotory kita bisa menggunakan method buildDefaultValidatorFactory() dari class Validation.
dan untunk membuat object Validator nya kita bisa menggunakan method getValidator() dari interface ValidationFactory.

contoh : 
``` java
	@Test
	public void testValidator() {
		/**
		 * method buildDefaultValidatorFactory() ini akan mendeteksi project kita daa mencari implementasi,
		 * atau libelary implementasi dari Bean Validation nya
		 * jikalau gaada maka akan gagal membuat object ValidatorFactory.
		 * dan akan terjadi exception dengan tipe NotProvideFoundException.
         * 
		 * sekarang ini kita menggunakan hibernate validator untuk implementasi
		 * dari Bean Validation nya, nanti jikalau kita ingin menggunakan liberary lain
		 * untuk impelemntasi dari Bean Validation nya itu bisa saja, asal penting 
		 * saat kita mau menggunakan Bean Validation kita harus menambahkan Implementasi dari
		 * Bean Validation nya.
		 */
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

		// membuat object Validation
		Validator validator = validatorFactory.getValidator();

		Assertions.assertNotNull(validator);

        validatorFactory.close();
	}
```
# Manual Validation
Sebelum menggunakan Bean Validation, untuk melakukan validasi di java, biasanya dilakukan secara manual.
biasanya kita menggunakan if else untuk melakukan validasi pengecekan.
Dan jika terjadi validasi error, biasanya kita akan membuat Exception.
Pada Bean Validation, cara kira memvalidasi berbeda, kita tidak melakukanya secara manual namun kita akan menggunakan Annotations yang bisa kita simpan pada field, Method, Paramter, dan lain lain.

# Constrain
Constrain merupakan Annotation yang digunakan sebagai penanda untuk target yang kita tambahkan misalnya Field, Method, Paramter dan lain-lain.
Bean Validation sudah menyediakan banyak sekali constrain yang bisa langsung kita gunakan.
Jika kita butuh validation yang berbeda, kita juga bisa membuat constrain secara manual.
Semua constrain Bean validation ada pada package jakarta.validation.constrain  
reference : https://jakarta.ee/specifications/bean-validation/3.0/apidocs/

contoh penggunakan constrain :
``` java
public class Person {
	/**
	 * @NotBlank -> artinya field nga boleh blank atau kosong
	 * @Size -> digunakan untk membatasi jumlah karkter
	 * 
	 * dan masih banyak lagi constrain yang bisa kita gunakan
	 * untuk lebih lengkapnya bisa lihat di dokumntasi yang terlah saya tuliskan diatas
	 * */
    @NotBlank(message = "nama depan gaboleh kosonk")
    @Size(max = 20, message = "nama depan gaboleh lebih dari 20 karakter")
    private String firstName;
    
    @NotBlank(message = "nama blakang gaboleh kosonk")
    @Size(max = 20, message = "nama blakang gaboleh lebih dari 20 karakter")
    private String lastNmae;
    
    public Person() {}

    public Person(String firstName, String lastNmae) {
        this.firstName = firstName;
        this.lastNmae = lastNmae;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNmae() {
        return lastNmae;
    }

    public void setLastNmae(String lastNmae) {
        this.lastNmae = lastNmae;
    }
}
```

# Constrain Violation 
Setelah kita menambahkan annotation constrain kepada class yang akan kita validasi, selanjutnya kita bisa mulai melakukan validasi terhadap Object class tersebut menggunakan method validate() milik class Validator.
Hasil kembalian dari method validate() adalah Set<ConstrainViolation>, yang mana constrain ciolation tersebut merupakan representasi kesalahan dari constrain.
Jika terjadi kesalahan pada constrain otomatis akan ada ConstrainViolation namun jika tidak ada kesalahan maka tidak akan ada ConstrainCiolation, alias Set nya (return value dari method validate()) akan berisi data kosong.

contoh :
``` java
public class ConstrainViolationTest {
    
    private ValidatorFactory falidatorFactory;

    private Validator validator;

    @BeforeEach
    public void setUp() {
        this.falidatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = this.falidatorFactory.getValidator();
    }

    @AfterEach
    public void tearDown() {
        this.falidatorFactory.close();
    }

    @Test
    public void testConstrainViolationFailed() {
        Person person = new Person();
        /**
         * saat kita memanggil validate(person), maka 
         * validator aka memvalidasi oject person, jikalau
         * terjadi error validasi maka error tersebut akan masuk ke
         * constrainViolation,
         * 
         * jadi, secara sigkatnya jikalau variabel constrainViolations 
         * itu ada isinya alias tidak null maka itu tidak error
         * dan jikalau pada variabel constrainViolations itu ada isinya
         * maka itu terjadi error.
         */
        Set<ConstraintViolation<Person>> constrainViolations = this.validator.validate(person);
        Assertions.assertEquals(2, constrainViolations.size());
        for (ConstraintViolation<Person> constrainViolation : constrainViolations) {
            System.out.println(constrainViolation.getMessage());
        }
    }
}
```

# Obejct Metadata
Pada ConstrtainViolation, tidak hanya error message yang bisa kita lihat, kita juga bisa melihat field manaya yang error dan object mana yang error dan lain-lain.
``` java
    @Test
    public void testObjectMetadata() {
        Person person = new Person();
        Set<ConstraintViolation<Person>> constrainViolations = this.validator.validate(person);
       for (ConstraintViolation<Person> constrainViolation : constrainViolations) {
            // untuk mendapatkan persan error
            System.out.println("Message : "+constrainViolation.getMessage());
            // untuk mendapatkan object yang di validasi
            System.out.println("Bean : "+constrainViolation.getLeafBean());
            // untuk mendapatkan annotasi yang menyebabkan error
            System.out.println("Constrain : "+constrainViolation.getConstraintDescriptor().getAnnotation());
            // untuk mendapatkan nilai yang tidak valid
            System.out.println("Invalid value : "+constrainViolation.getInvalidValue());
            // untuk mendapatkan field yang terjadi error validasi
            System.out.println("Path : "+constrainViolation.getPropertyPath());
       }
    }
```

# Nested Validation
Jika terjadi nested object, Bean Validation tidak akan validasi terhadap object tersebut.
Misal kita punya class Person, yang mana class Person memiliki field atau properti dengan tipe class Address, secara defaut class Address tidak akan di validasi.
Jika kita ingin melakukan validasi terhadap nested object tesebut, kita perlu manambahkan annotation @Valid.
@Valid juga bisa digunakan untuk nested object yang terdapat didalam array atau collection.

cotnohnya kita memiliki class Addres
``` java
import jakarta.validation.constraints.NotBlank;

public class Address {
    
    @NotBlank(message = "nama jalan gaboleh kosong")
    private String street;
    
    @NotBlank(message = "nama kota gaboleh kosong")
    private String city;
    
    @NotBlank(message = "nama negara gaboleh kosong")
    private String country;

    public Address() {
    }

    public Address(String street, String city, String country) {
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
```

dan kita ingin menambajakan field dengan tipe Addres pada class person kita
``` java
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Person {

    @NotBlank(message = "nama depan gaboleh kosonk")
    @Size(max = 20, message = "nama depan gaboleh lebih dari 20 karakter")
    private String firstName;
    
    @NotBlank(message = "nama blakang gaboleh kosonk")
    @Size(max = 20, message = "nama blakang gaboleh lebih dari 20 karakter")
    private String lastNmae;
    
    @NotNull(message = "allamat gaboleh kosonk")
    /**
     * jika kita ingin object address nya di validasi
     * maka kita harus meng annotasi dengan annotasi @Valid,
     * karna jikalau kita tidak annotasi dengan @Valid 
     * by default object address tidak akan pernah di validasi
     * jadi meng annotasi dengan @NotBlank saja tidak cukup
     */
    @Valid
    private Address address;

    public Person() {}

    public Person(String firstName, String lastNmae, Address address) {
        this.firstName = firstName;
        this.lastNmae = lastNmae;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddres(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNmae() {
        return lastNmae;
    }

    public void setLastNmae(String lastNmae) {
        this.lastNmae = lastNmae;
    }

    @Override
    public String toString() {
        return "Person [firstName=" + firstName + ", lastNmae=" + lastNmae + "]";
    }
}
```
Setelah field Addres di annotasi dengan @Valid maka field Address tersebut akan di validasi
```java

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class NestedValidationTest {
    
    private Validator validator;

    @BeforeEach
    public void setUp() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testNestedValidation() {
        Person person = new Person();
        person.setFirstName("alliano");
        person.setLastNmae("alfarez");

        Address address = new Address();

        person.setAddres(address);
        /**
         * disini akan terjadi beberapa exception karena object Address
         * kita belum set value nya
         * */
        Set<ConstraintViolation<Person>> violations = this.validator.validate(person);

        for (ConstraintViolation<Person> violation : violations) {
            System.out.println("message : "+violation.getMessage()); 
            System.out.println("message : "+violation.getPropertyPath());
        }
    }
}
```

# Hibernate Validation Constraiain
Selain annotation Constrain yang terdapat pada Bean Validation.
Hibbernate Validator juga menyediakan Constrain tambahan.
Kita bisa melihatnya di package org.hibernate.validator.constrains
reference : https://docs.jboss.org/hibernate/stable/validator/api/org/hibernate/validator/constrains/package-summary.html

contoh :
kita memiliki kelas payment yang akan di validasi oleh annotation dari Hibernate Validator
``` java
import org.hibernate.validator.constraints.LuhnCheck;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Payment {
    
    @NotBlank(message = "order id gaboleh kosong")
    private String orderId;

    /**
     * @Range adalah Constrain dari Hibernate Validator
     * fungsi @Range sendiri sama seperti @Min dan @Max pada
     * Bean Validation,
     * keuntungan dari @Range ini kita bisa memberi max simal dan minimal
     * value dengan 1 annotasi,
     * berbeda dengan Bean validation yang membutuhkan 2 annotasi yaitu 
     * @Min dan @Max
     */
    @Range(min = 10_000L, max = 100_000_000, message = "amount gaboleh kurang dari 10.000 dan gaboleh lebih dari 100.000.000")
    @NotNull(message = "ammount gaboleh kosong")
    private Long amount;

    /**
     * @LunCheck adalah annotasi dari Hibbernate Validator
     * yang mana fungsi dari @LunCheck ini untuk memvalidasi 
     * sebuah nomer kartu keredit
     * 
     * dan sebenarnya masih banayak lagi constrain yang diberikan
     * oleh Hibbernate Validaor
     */
    @LuhnCheck(message = "kartu keredit tidak valid")
    @NotBlank(message = "kartu keredit gaboleh kosong")
    private String creditCard;

    public Payment() {
    }

    public Payment(String orderId, Long amount, String creditCard) {
        this.orderId = orderId;
        this.amount = amount;
        this.creditCard = creditCard;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "Payment [orderId=" + orderId + ", amount=" + amount + ", creditCard=" + creditCard + "]";
    }
}
```

kita buat abstrac claass untuk mengatasi kode yang rendudan misalnya seperti pembuatan pembuatan object Validator dan ValidatorFactory pada setiap class unit test, disini kita menggunakan unti test hanya untuk pengetesan saja, pada real study case biasanya nanti kita tidak menggunakan unit test.
``` java
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public abstract class AbstracValidatorTest {
    
    protected ValidatorFactory validatorFactory;

    protected Validator validator;

    @BeforeEach
    public void setUp() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = this.validatorFactory.getValidator();
    }

    @AfterEach
    public void tearDown() {
        this.validatorFactory.close();
    }

    public void validate(Object object) {
        Set<ConstraintViolation<Object>> violations = this.validator.validate(object);
        for (ConstraintViolation<Object> violation : violations) {
            System.out.println("Error message : "+violation.getMessage());
            System.out.println("Error Field : "+violation.getPropertyPath());
        }
    }
}
```

lalu kita test dengan menggunakan bantuan unit test, pada real study case mungkin nanti kita tidak akan menggunakan unit test melaikan langsung di layer model pada applikasi kita.
``` java
public class HibbernateValidationConstrainTest extends AbstracValidatorTest {
    
    @Test
    public void testHibernateVaidationCOnstrainInvalid() {
        Payment payment = new Payment();
        payment.setAmount(1000L);
        payment.setCreditCard("0092");
        payment.setOrderId("001");
        
        validate(payment);
    }

    @Test
    public void testHibernateVaidationConstrainFalid() {
        Payment payment = new Payment();
        payment.setAmount(80_000L);
        payment.setCreditCard("4111111111111111");
        payment.setOrderId("001");
        validate(payment);
    }
}
```

# Grouping COnstrain
Secara default, saat Validator melakukan validasi, Validator akan memvalidasi semua field yang di annotasi Constrain.
Kadang, ada kalanya saat kita misalnya ingin melakukan pengecekan beberapa hal saja pada kondisi tertentu, misal terdapat satu class yang digunakan utuk beberapa aksi, sehingga membutuhkan kombinasi validasi yang berbeda.
Dalam study case ini kita akan menggunakan grouping constrain.
Grouping constrain merukapan teknik menelompokan constrain yang terdapat pada class.
Dengan menggunakan Group, saat melakukan validasi kita bisa memilih group mana yang akan divalidasi.
Semua Constrain bisa memiliki lebih dari 1 group.

Untuk mengrouping constrain kita membuatuhkan flaging atau mark dengan menggunkan interface kosong.
``` java
public interface CreditCardPaymentGroup { }
```
``` java
public interface VirtualAccountPaymentGroup { }
```

cara untuk mengrouping nya sebagai berikut :
``` java
 /**
  * untuk mengrouping suatu field kita cukup tambahkan
  * paramter groups pada constrain nya dan diikuti dengan 
  * interface yang telah kita buat untuk flaging dalam
  * kasus ini adalah  VirtualAccountPaymentGroup dan
  * CreditCardPaymentGroup
  */
 @NotBlank(message = "order id can't be blank", groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class})
 private String orderId;

 @Range(min = 10_000L, max = 100_000_000, message = "amount can't les than 10.000 and can't more than 100.000.000", groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class})
 @NotNull(message = "amount can't be blank", groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class})
 private Long amount;

 @LuhnCheck(message = "credit card is invalid", groups = {CreditCardPaymentGroup.class})
 @NotBlank(message = "credit card can't be blank", groups = {CreditCardPaymentGroup.class})
 private String creditCard;

 @NotBlank(message = "virtual account can't be blank", groups = {VirtualAccountPaymentGroup.class})
 private String virtualAccount;
```

untuk mengetes grouping yang telah kita buat kita bisa menggunakan junit, nanti pada real case
mugkin kita tidak menggunakan unit test, melainkan langsung pada layer pada applikasi.
``` java
import org.junit.jupiter.api.Test;

public class GroupTest extends AbstracValidatorTest {
    
    @Test
    public void testCreditCardPaymentGroupFaill() {
        Payment payment = new Payment();
        payment.setAmount(20_000L);
        payment.setCreditCard("011");
        payment.setOrderId("001");
        payment.setVirtualAccount("+628");
        validateWithGroups(payment, CreditCardPaymentGroup.class);      
    }

    @Test
    public void testCreditCardPaymentGroupSuccess() {
        Payment payment = new Payment();
        payment.setAmount(20_000L);
        payment.setCreditCard("4111111111111111");
        payment.setOrderId("001");
        payment.setVirtualAccount("+628");
        /**
         * secara degault jikau constrain pada class payment itu kita 
         * grouping maka Vaidator tidak akan memvalidasi nya.
         * Jikalau kita ingin Validator tetap memvalidasi walaupun kita sudah grouping
         * maka kita harus menambahkan flagind Default.class pada constrain nya.
         * 
         * agar validator memvalidasinya kita harus tambahakan paramter grouping pada 
         * saat kita validasi.
         * 
         * jikalau kita hanya meberikan 1 groub saja misalnya VirtualAccountPaymentGroup
         * maka field yang akan di validasi hanya field yang memiliki group VirtualAccountPaymentGroup
         * saja
         */
        validateWithGroups(payment,VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class);
    }
}
```
# Group Sequence
Saat kita melakukan validasi dengan beberapa group, tidak ada jaminan bahwa sebuah group akan dijalankan sebelum group yang lain.
Bean Validation memiliki annotasi @GroupSequence yang bisa digunakan untuk menentukan tahapan group mana terlebih dahulu yang akan di validasi.
Untuk membggunakan annotasi @GroupSequence kita terlebih dahulu membuat interface flaging utuk group lalu tambahkan annotasi @GroupSequence.
``` java
/**
 * jadi jikalau nanti terjadi error validasi pada 
 * group CreditCardPaymentGroup maka 
 * group selanjutnya tidak akan di eksekusi
 */
@GroupSequence(value = {
    CreditCardPaymentGroup.class,
    VirtualAccountPaymentGroup.class
})
public interface PaymentGroup { }
```

test @GroupSequence
``` java
public class GroupSequenceTest extends AbstracValidatorTest {
    
    @Test
    public void testGroupSequenceFaill() {
        Payment payment = new Payment();
        validateWithGroups(payment, PaymentGroup.class);
    }

    @Test
    public void testGroupSequenceSuccess() {
        Payment payment = new Payment();
        payment.setAmount(50_000_000L);
        payment.setCreditCard("4111111111111111");
        payment.setOrderId("001");
        payment.setVirtualAccount("+62813");
        validateWithGroups(payment, PaymentGroup.class);
    }
}
```

# Group Conversion
Kadang ada kasus yangmna terdapat sebuah class yang sudah memiliki field group, namun ternyata kita membutuhkan class tersebut unutk di embed di class lain, sedangkan class lain tersebut menggunakan group berbeda.
Pada kasus seperti ini kita bisa meng konversi group.
Untuk melakukan konversi group, kita busa menggunakan annotasi @ConvertGroup lalu tentukan dari group apa ke group apa.

misalnya kita memiliki class Customer yang akan di embed di class Payment
``` java
public class Customer {
    
    @Email(message = "email is invalid")
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "name is required")
    private String name;

    public Customer() {
    }

    public Customer(
        @Email(message = "email is invalid") 
        @NotBlank(message = "email is required") String email,
        @NotBlank(message = "name is required") String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer [email=" + email + ", name=" + name + "]";
    }
}
```
lalu kita embed di class Payment 
``` java
    /**
     * ini walaupun kita telah meng annotasi @Valid pada filed ini
     * akan tetapi field ini datanya tidak akan pernah di validasi oleh 
     * java bean vaidation dikarnakan field ini di flaging/grouping
     * dengan VirtualAccountPaymentGroup dan CreditCardPaymentGroup
     * sedangkan field data customer ini kita tidak grouping samasekali
     * maka field tersebut akan menggunakan flaging/groping Default
     * 
     * agar data dari field customer di validasi juga, kita harus mengkonversi 
     * flaging nya dengan menggunakan annotasi @ConvertGroup
     */
    @Valid
    @NotNull(message = "customer can't be null", groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class})
    @ConvertGroup(from = VirtualAccountPaymentGroup.class, to = Default.class)
    @ConvertGroup(from = CreditCardPaymentGroup.class, to =  Default.class)
    private Customer customer;
```
lalu kita bisa validasi
``` java
public class ConvertGroupTest extends AbstracValidatorTest {

    @Test
    public void testConvertGroupFailed() {
        Payment payment = new Payment();
        payment.setOrderId("001");
        payment.setAmount(30_000L);
        payment.setCreditCard("4111111111111111");
        payment.setVirtualAccount("+62813");
        Customer customer = new Customer();
        payment.setCustomer(customer);

        validateWithGroups(payment, VirtualAccountPaymentGroup.class);
    }

    @Test
    public void testConvertGroupFailedSuccess() {
        Payment payment = new Payment();
        payment.setOrderId("001");
        payment.setAmount(30_000L);
        payment.setCreditCard("4111111111111111");
        payment.setVirtualAccount("+62813");
        Customer customer = new Customer();
        customer.setEmail("allianoanoanymous@gmail.com");
        customer.setName("Alliano");
        payment.setCustomer(customer);
        validateWithGroups(payment, VirtualAccountPaymentGroup.class);
    }
}
```

# Payload 
Secara default aturan annotation constrain di bean validation selain memiliki method message, groups, ada juga payload.
Method payload itu sebenarnya tidak digunakan sama sekali oleh bean validation, namun method ini bisa kita gunakan untuk menambahkan informasi ketika menggunakan constrain.
reference : https://jakarta.ee/specifications/bean-validation/3.0/apidocs/jakarta/validation/payload

# Membuat Payload
Sekarang kita akan mencoba membuat payload yang sederhana.
Payload yang akan kita buat adalah sebuah class yang mna jika terjadi validation error, kita akan gunakan payload tersebut unutk mengirim notifikasi.

kode membuat payload.
``` java
public class EmailErrorPayload implements Payload {
    
    public void sendEmail(ConstraintViolation<?> violation) {
        System.out.println("payload trigered when error : "+violation.getMessage());
    }
}
```
menggunakan payload pada constrain.
``` java
@NotBlank(message = "virtual account can't be blank", groups = {VirtualAccountPaymentGroup.class}, payload = {EmailErrorPayload.class})
private String virtualAccount;
```
test payload
``` java
public class PayloadTest extends AbstracValidatorTest {

    @Test
    public void testPayload() {
        Payment payment = new Payment();
        payment.setOrderId("001");
        payment.setAmount(50_000L);
        payment.setCreditCard("4111111111111111");
        Customer customer = new Customer();
        customer.setEmail("allianoanonymous@gmail.com");
        customer.setName("alliano");
        payment.setCustomer(customer);
        /**
         * disini kita tidak meng set nila dari fild virtual account
         * harapanya nanti akan terjadi error lalu error tersebut kita akan 
         * ambil payload nya dan mentriger nya
         */
       Set<ConstraintViolation<Payment>> violations = this.validator.validate(payment, VirtualAccountPaymentGroup.class);
       for (ConstraintViolation<Payment> violation : violations) {
           System.out.println("error field : "+violation.getPropertyPath());
           violation.getConstraintDescriptor().getPayload().forEach( aClass -> {
                if(aClass == EmailErrorPayload.class) {
                    EmailErrorPayload emailErrorPayload = new EmailErrorPayload();
                    emailErrorPayload.sendEmail(violation);
                }
           });
       }
    }
}
```