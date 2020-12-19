# PARTE TEORICA

### Arquitecturas de UI: MVP, MVVM y MVI

#### MVVM

##### ¿En qué consiste esta arquitectura?
Esta arquitectura es un patrón en el que separar los elementos de una aplicación en tres bloques: View, ViewModel y Model.

El modelo accede a los datos y los expone al ViewModel. Además se encarga de actualizarlos cuando sea necesario.

El ViewModel recoge los datos del Model y los expone a la vista, poniendo a disposición de esta tanto los datos como eventos para que las vistas se actualicen si los datos cambian.

La vista (View) dibuja la interfaz con la que interacciona el usuario. En android está representada a través de _Activities_ y _Fragments_. La vista obtiene los datos que mostrar de uno o varios ViewModel.

##### ¿Cuáles son sus ventajas?
En esta arquitectura el ViewModel mantiene los datos en uso por las vistas, de tal forma que estos no se pierden en caso de que la actividad se destruya o pase a un estado en que las vistas se destruyan. De hacer esto de manera nativa, con _onSaveInstanceState()_, solo podríamos guardar datos nativos.

Además, esta arquitectura permite probar mediante test unitarios la lógica de negocio, al estar separada de las vistas, contenida en el ViewModel.

##### ¿Qué inconvenientes tiene?
La desventaja de esta arquitectura es que puede complicar la implementación si la aplicación es muy simple, añadiendo una abstracción que podría no ser necesaria.

#### MVP

##### ¿En qué consiste esta arquitectura?
Esta arquitectura cuenta con tres componentes: Model, View y Presentation. El modelo se encarga de acceder a los datos y también los trata para que sean presentados. La vista (View), al igual que la anterior presenta la interfaz con la que el usuario interacciona. Por último, el Presenter, se encarga de mediar entre el modelo y la vista.

##### ¿Cuáles son sus ventajas?
Esta arquitectura permite crear los test unitarios de forma más sencilla al poder crear instancias del Presenter, que interacciona con el resto de elementos.

##### ¿Qué inconvenientes tiene?
Como inconveniente, la vista puede haber sido destruida cuando el Presenter termine de procesar alguna operación, por lo que deberá tenerse en cuenta esto en la implementación. Además, habrá que tener en cuenta cuándo se destruye el Presenter, ya que de tener la vista una instancia de este, puede provocar que no se libere la memoria correspondiente a estos elementos.

#### MVI

##### ¿En qué consiste esta arquitectura?
MVI son las siglas de Model-View-Intent. Esta arquitectura es muy diferente a las anteriores ya que es unidireccional y cíclica.
Cuenta con tres elementos: 
 * El modelo representa el estado de la aplicación y es inmutable. Esto significa que si se requiere una modificación en el modelo, se creará uno nuevo.
 * El Presenter, se encargará de notificar a la vista el estado de la aplicación y el cambio de estado cuando este se produzca.
 * La vista, que mostrará la interfaz que corresponda a cada estado de la aplicación y asignará a los elementos de interacción de la interfaz el cambio de estado que provoca cada acción.

El _Intent_ mencionado anteriormente no representa una capa o una clase sino una acción que se traduce en un cambio de estado en la aplicación.

##### ¿Cuáles son sus ventajas?
Las ventajas de esta arquitectura son la consistencia del estado durante el ciclo de vida de las vistas, los modelos inmutables que permiten asegurar las operaciones multihilo y su flujo de trabajo unidireccional y cíclico.

##### ¿Qué inconvenientes tiene?
La mayor desventaja de esta arquitectura reside en su aprendizaje, ya que requiere más conocimientos para que pueda ser entendida y aplicada.

---

### Testing

#### ¿Qué tipo de tests se deberían incluir en cada parte de la pirámide de test? Pon ejemplos de librerías de testing para cada una de las partes. 
La piramide de test se divide en tres. _Small_ test, _Medium_ test y _Large_ test.

Los _small_ tests son test unitarios de funcionalidades concretas de la aplicación con el comportamiento esperado. Son los más rápidos. Las librerías más utilizadas en estos test son JUnit y Mockito.

Los _medium_ tests son test de integración y cómo la aplicación interacciona entre sus elementos y los del framework de Android. La librería más utilizada es Roboelectric, que permite ejecutar los test en un sandbox.

Los _large_ test son test de integración y de interfaz gráfica (UI). Son los más lentos y requiren un dispositivo, ya sea emulado o físico. Las librerías más utilizadas son Espresso y UI Automator.

#### ¿Por qué los desarrolladores deben centrarse sobre todo en los Unido Tests?
Los desarrolladores deben centrarse en los test unitarios por varias razones. Son los test más rápidos al no necesitar ejecutarse sobre un emulador o sobre un dispositivo. No requieren librerías, ya que estos test prueban el comportamiento específico de la aplicación, sin interaccionar con clases específicas de Android. Por último, probar los elementos de forma individual los hace más fiables al integrarse con el resto de elementos de la app.

---

### Inyección de dependencias

#### Explica en qué consiste y por qué nos ayuda a mejorar nuestro código.
Escribe aquí tu respuesta

#### Explica cómo se hace para aplicar inyección de dependencias de forma manual a un proyecto (sin utilizar librerías externas).
Escribe aquí tu respuesta
