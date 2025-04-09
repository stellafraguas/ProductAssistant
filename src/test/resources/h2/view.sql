CREATE OR REPLACE VIEW v_category AS
WITH RECURSIVE category_hierarchy AS (
    SELECT
        id,
        name,
        parent_id,
        CAST(name AS VARCHAR) AS display_name
    FROM category
    WHERE parent_id IS NULL

    UNION ALL

    SELECT
        c.id,
        c.name,
        c.parent_id,
        CAST((ch.display_name || ' > ' || c.name) AS VARCHAR)
    FROM category c
    JOIN category_hierarchy ch ON c.parent_id = ch.id
)
SELECT * FROM category_hierarchy;
