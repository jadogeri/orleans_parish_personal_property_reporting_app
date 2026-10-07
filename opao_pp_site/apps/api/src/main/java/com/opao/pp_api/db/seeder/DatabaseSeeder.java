package com.opao.pp_api.db.seeder   ;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final BusinessTypeSeeder businessTypeSeeder;

    // Inject all required seeders seamlessly
    public DatabaseSeeder(BusinessTypeSeeder businessTypeSeeder) {
        this.businessTypeSeeder = businessTypeSeeder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Parse command line runner arguments list (mirrors NestJS process.argv flags)
        boolean forceSeed = Arrays.asList(args).contains("--seed");

        if (forceSeed) {
            System.out.println("🌱 Activating Central Database Data Seeding Engine...");
            
            // 💡 Sequential execution ensures baseline data maps out safely top-down
            this.businessTypeSeeder.seed();
            
            System.out.println("✅ All target datasets processed successfully!");
        }
    }
}
