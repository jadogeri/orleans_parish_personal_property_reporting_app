package com.opao.pp_api.db.seeder;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class BusinessTypeSeeder implements Seeder {

    private final JdbcTemplate jdbcTemplate;

    public BusinessTypeSeeder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void seed() throws Exception {
        System.out.println("  ↳ 🏬 Ingesting Business Types from Monorepo Static-Data Package...");

        // Resolves the file cleanly out of your mapped classpath targets
        ClassPathResource resource = new ClassPathResource("static-data/business_types.csv");
        List<String> insertQueries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Gracefully bypass the CSV column header labels
                    continue;
                }

                // Split fields by standard comma formatting
                String[] tokens = line.split(",");
                if (tokens.length >= 2) {
                    String codeStr = tokens[0].trim();
                    String description = tokens[1].trim();

                    // Escape single quote characters to prevent SQL syntax parsing failures
                    String safeDescription = description.replace("'", "''");

                    // 💡 FIXED: Columns matched exactly to business_code and business_description
                    String sql = String.format(
                        "INSERT INTO business_type (business_code, business_description) " +
                        "SELECT %s, '%s' " +
                        "WHERE NOT EXISTS (SELECT 1 FROM business_type WHERE business_code = %s)",
                        codeStr, safeDescription, codeStr
                    );
                    
                    insertQueries.add(sql);
                }
            }
        }

        // Execute all generated sql insertions as a single transaction step
        if (!insertQueries.isEmpty()) {
            jdbcTemplate.batchUpdate(insertQueries.toArray(new String[0]));
            System.out.println("    ✅ Business types database ingestion batch completed successfully.");
        } else {
            System.out.println("    ⚠️ Warning: No valid data entries discovered inside business_types.csv.");
        }
    }
}
