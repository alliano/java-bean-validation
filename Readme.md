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