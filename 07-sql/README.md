# 07 · SQL for Java Developers

Joins, window functions, indexes, transactions, isolation, JDBC, injection, JPA, N+1, and 27 most-asked coding questions. Practice DB: H2 in-memory.

- Handbook: https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `sql.html`
- Chapters: **24** (one branch each) · Review files: **3**
- Study order across handbooks: [CHECKLIST.md](../CHECKLIST.md)

## Chapters

| # | Chapter | Branch | Folder | Handbook anchor |
|---|---|---|---|---|
| 01 | Why SQL matters & the sample database | `topic/07-sql/01-why-sql-sample-db` | [chapters/c01_why_sql_sample_db](chapters/c01_why_sql_sample_db) | `why` |
| 02 | SELECT basics | `topic/07-sql/02-select-basics` | [chapters/c02_select_basics](chapters/c02_select_basics) | `select` |
| 03 | Execution order | `topic/07-sql/03-execution-order` | [chapters/c03_execution_order](chapters/c03_execution_order) | `order` |
| 04 | NULL | `topic/07-sql/04-null-handling` | [chapters/c04_null_handling](chapters/c04_null_handling) | `nulls` |
| 05 | GROUP BY & HAVING | `topic/07-sql/05-group-by-having` | [chapters/c05_group_by_having](chapters/c05_group_by_having) | `groupby` |
| 06 | JOINs | `topic/07-sql/06-joins` | [chapters/c06_joins](chapters/c06_joins) | `joins` |
| 07 | Subqueries & CTEs | `topic/07-sql/07-subqueries-ctes` | [chapters/c07_subqueries_ctes](chapters/c07_subqueries_ctes) | `subqueries` |
| 08 | Window functions | `topic/07-sql/08-window-functions` | [chapters/c08_window_functions](chapters/c08_window_functions) | `windows` |
| 09 | CASE, functions, UNION | `topic/07-sql/09-case-functions-union` | [chapters/c09_case_functions_union](chapters/c09_case_functions_union) | `functions` |
| 10 | DDL, constraints, DML | `topic/07-sql/10-ddl-constraints-dml` | [chapters/c10_ddl_constraints_dml](chapters/c10_ddl_constraints_dml) | `ddl` |
| 11 | Keys & normalization | `topic/07-sql/11-keys-normalization` | [chapters/c11_keys_normalization](chapters/c11_keys_normalization) | `normalization` |
| 12 | Views & triggers | `topic/07-sql/12-views-triggers` | [chapters/c12_views_triggers](chapters/c12_views_triggers) | `views` |
| 13 | Indexes | `topic/07-sql/13-indexes` | [chapters/c13_indexes](chapters/c13_indexes) | `indexes` |
| 14 | Transactions & ACID | `topic/07-sql/14-transactions-acid` | [chapters/c14_transactions_acid](chapters/c14_transactions_acid) | `transactions` |
| 15 | Isolation & locking | `topic/07-sql/15-isolation-locking` | [chapters/c15_isolation_locking](chapters/c15_isolation_locking) | `isolation` |
| 16 | Query performance | `topic/07-sql/16-query-performance` | [chapters/c16_query_performance](chapters/c16_query_performance) | `performance` |
| 17 | JDBC | `topic/07-sql/17-jdbc` | [chapters/c17_jdbc](chapters/c17_jdbc) | `jdbc` |
| 18 | SQL injection | `topic/07-sql/18-sql-injection` | [chapters/c18_sql_injection](chapters/c18_sql_injection) | `injection` |
| 19 | Transactions, batching, pools | `topic/07-sql/19-jdbc-tx-batching-pools` | [chapters/c19_jdbc_tx_batching_pools](chapters/c19_jdbc_tx_batching_pools) | `jdbcmore` |
| 20 | JPA & Hibernate | `topic/07-sql/20-jpa-hibernate` | [chapters/c20_jpa_hibernate](chapters/c20_jpa_hibernate) | `jpa` |
| 21 | The N+1 problem | `topic/07-sql/21-n-plus-1` | [chapters/c21_n_plus_1](chapters/c21_n_plus_1) | `nplus1` |
| 22 | Spring Data JPA | `topic/07-sql/22-spring-data-jpa` | [chapters/c22_spring_data_jpa](chapters/c22_spring_data_jpa) | `springdata` |
| 23 | @Transactional & locking | `topic/07-sql/23-transactional-locking` | [chapters/c23_transactional_locking](chapters/c23_transactional_locking) | `springtx` |
| 24 | 27 coding questions | `topic/07-sql/24-coding-questions` | [chapters/c24_coding_questions](chapters/c24_coding_questions) | `questions` |

## Review (no branch)

- [Dialect cheat sheet](review/dialect-cheat-sheet.md)
- [Theory Q&A](review/theory-qa.md)
- [Quiz](review/quiz.md)

## Build / run

```bash
mvn -q -pl 07-sql compile
mvn -q -pl 07-sql compile exec:java -Dexec.mainClass=<package>.Practice
```
