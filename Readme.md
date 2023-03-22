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
Setelah menggunakan Bean Validation kita akan membuat Validasi nya terpusat, jadi layer-layer lain seprrti Presentation Layer, Bussines Layer, dan Data Access layer akan melakuakn custom validasi secara rendudan.

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

```
