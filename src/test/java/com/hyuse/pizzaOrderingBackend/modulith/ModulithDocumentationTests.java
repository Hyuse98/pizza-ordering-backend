package com.hyuse.pizzaOrderingBackend.modulith;

import com.hyuse.pizzaOrderingBackend.PizzaOrderingBackendApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootTest
class ModulithDocumentationTests {

    @Test
    void createModuleDocumentation() {
        ApplicationModules modules = ApplicationModules.of(com.hyuse.pizzaOrderingBackend.PizzaOrderingBackendApplication.class);

        new Documenter(modules)
                .writeDocumentation();
    }

    @Test
    void verifyModuleDependencies() {
        ApplicationModules modules = ApplicationModules.of(PizzaOrderingBackendApplication.class);

        modules.verify();
    }

    @Test
    void createCustomDocumentation() {
        ApplicationModules modules = ApplicationModules.of(PizzaOrderingBackendApplication.class);

        new Documenter(modules)
                .writeDocumentation()
                .writeIndividualModulesAsPlantUml()
                .writeModulesAsPlantUml();
    }

//    @Test
//    void createCustomizedDocumentation() {
//        ApplicationModules modules = ApplicationModules.of(PizzaOrderingBackendApplication.class);
//
//        new Documenter(modules)
//                .withCustomPlantUmlSprite("pizza", "path/to/pizza.puml")
//                .withTargetFolder("custom-docs")
//                .withComponentScan(true)  // Documenta componentes dentro dos módulos
//                .withColorSelector(moduleName -> moduleName.equals("order") ? "#ff0000" : "#000000")
//                .writeDocumentation();
//    }

    @Test
    void writeDocumentationSnippets() {

        ApplicationModules modules = ApplicationModules.of(PizzaOrderingBackendApplication.class);
        new Documenter(modules)
                .writeModuleCanvases();
    }
}
