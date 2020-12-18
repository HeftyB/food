package com.lambdaschool.foundation;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.lambdaschool.foundation.models.*;
import com.lambdaschool.foundation.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.Locale;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData
    implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    RecipeService recipeService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception
    {
        userService.deleteAll();
        roleService.deleteAll();
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        // admin, data, user
        User u1 = new User("admin",
            "password",
            "admin@email.com");
        u1.getRoles()
            .add(new UserRoles(u1,
                r1));
        u1.getRoles()
            .add(new UserRoles(u1,
                r2));
        u1.getRoles()
            .add(new UserRoles(u1,
                r3));
        u1.getUseremails()
            .add(new Useremail(u1,
                "admin@email.local"));
        u1.getUseremails()
            .add(new Useremail(u1,
                "admin@mymail.local"));

        userService.save(u1);


        Category c1 = new Category("Meat");
        Category c2 = new Category("Herbs");
        Category c3 = new Category("Spices");
        Category c4 = new Category("Dairy");
        Category c5 = new Category("Fruits");
        Category c6 = new Category("Produce");
        Category c7 = new Category("Frozen");
        Category c8 = new Category("Canned");
        Category c9 = new Category("Pantry");
        Category c10 = new Category("Bread");
        Category c11 = new Category("Other");

        c1 = categoryService.save(c1);
        c2 = categoryService.save(c2);
        c3 = categoryService.save(c3);
        c4 = categoryService.save(c4);
        c5 = categoryService.save(c5);
        c6 = categoryService.save(c6);
        c7 = categoryService.save(c7);
        c8 = categoryService.save(c8);
        c9 = categoryService.save(c9);
        c10 = categoryService.save(c10);
        c11 = categoryService.save(c11);

        Ingredient i1 = new Ingredient("All-Purpose Flour",
            c9);
        Ingredient i2 = new Ingredient("Fresh Ground Pepper",
            c3);
        Ingredient i3 = new Ingredient("Stewing Meat",
            c1);
        Ingredient i4 = new Ingredient("Vegetable Oil",
            c9);
        Ingredient i5 = new Ingredient("Red Wine Vinegar",
            c9);
        Ingredient i6 = new Ingredient("Red Wine",
            c11);
        Ingredient i7 = new Ingredient("Beef Broth",
            c9);
        Ingredient i8 = new Ingredient("Bay Leaves",
            c3);
        Ingredient i9 = new Ingredient("Onions",
            c6);
        Ingredient i10 = new Ingredient("Carrots",
            c6);
        Ingredient i11 = new Ingredient("Potatoes",
            c6);
        Ingredient i12 = new Ingredient("Salt",
            c3);

        i1 = ingredientService.save(i1);
        i2 = ingredientService.save(i2);
        i3 = ingredientService.save(i3);
        i4 = ingredientService.save(i4);
        i5 = ingredientService.save(i5);
        i6 = ingredientService.save(i6);
        i7 = ingredientService.save(i7);
        i8 = ingredientService.save(i8);
        i9 = ingredientService.save(i9);
        i10 = ingredientService.save(i10);
        i11 = ingredientService.save(i11);
        i12 = ingredientService.save(i12);

        Recipe rec1 = new Recipe();

        rec1.setRecipename("Beef Stew");
        rec1.setDescription("A delicious old time stew");
        rec1.setDirections("Combine the flour and pepper in a bowl, add the beef and toss to coat well. Heat 3 teaspoons of the oil in a large pot." +
            " Add the beef a few pieces at a time; do not overcrowd. Cook, turning the pieces until beef is browned on all sides, about 5 minutes per batch;" +
            " add more oil as needed between batches.\n" +
            "Remove the beef from the pot and add the vinegar and wine. Cook over medium-high heat, scraping the pan with a wooden spoon to loosen any browned bits." +
            " Add the beef, beef broth and bay leaves. Bring to a boil, then reduce to a slow simmer.\n" +
            "Cover and cook, skimming broth from time to time, until the beef is tender, about 1 1/2 hours. Add the onions and carrots and simmer, covered, for 10 minutes." +
            " Add the potatoes and simmer until vegetables are tender, about 30 minutes more. Add broth or water if the stew is dry." +
            " Season with salt and pepper to taste. Ladle among 4 bowls and serve.");

        rec1.getIngredients().add(new RecipeIngredient(rec1, i1, "1/4 cup"));
        rec1.getIngredients().add(new RecipeIngredient(rec1, i2, "1/4 tsp"));
        rec1.getIngredients().add(new RecipeIngredient(rec1, i3, "1LB"));
        rec1.getIngredients().add(new RecipeIngredient(rec1, i4, "5 tsp"));
        rec1.getIngredients().add(new RecipeIngredient(rec1, i5, "2 tbsp"));
        rec1.getIngredients().add(new RecipeIngredient(rec1, i6, "1 cup"));
        rec1.getIngredients().add(new RecipeIngredient(rec1, i7, "3 1/2 cups"));
        rec1.getIngredients().add(new RecipeIngredient(rec1, i8, "2"));
        rec1.getIngredients().add(new RecipeIngredient(rec1, i9, "1 medium"));
        rec1.getIngredients().add(new RecipeIngredient(rec1, i10, "5 medium"));
        rec1.getIngredients().add(new RecipeIngredient(rec1, i11, "3 large"));
        rec1.getIngredients().add(new RecipeIngredient(rec1, i12, "2 tsp"));

        rec1 = recipeService.save(rec1);

        if (false)
        {
            // using JavaFaker create a bunch of regular users
            // https://www.baeldung.com/java-faker
            // https://www.baeldung.com/regular-expressions-java

            FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
                new RandomService());
            Faker nameFaker = new Faker(new Locale("en-US"));

            for (int i = 0; i < 25; i++)
            {
                new User();
                User fakeUser;

                fakeUser = new User(nameFaker.name()
                    .username(),
                    "password",
                    nameFaker.internet()
                        .emailAddress());
                fakeUser.getRoles()
                    .add(new UserRoles(fakeUser,
                        r2));
                fakeUser.getUseremails()
                    .add(new Useremail(fakeUser,
                        fakeValuesService.bothify("????##@gmail.com")));
                userService.save(fakeUser);
            }
        }
    }
}