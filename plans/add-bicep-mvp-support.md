---
name: Add Bicep MVP support
issue: local-fork
state: complete
version: 0.1
---

## Goal

Add fork-local Bicep support for file detection, metrics, and text extraction using the bundled Tree-sitter Bicep JAR. Dependency analysis and upstream integration are out of scope.

## Tasks

### 1. Register Bicep
- Add failing language-support tests.
- Wire the local parser JAR, language enum, registry, and definition.

### 2. Add MVP metrics
- Add failing tests for control flow, functions, parameters, and comments.
- Implement and refactor the Bicep metric mapping.

### 3. Add MVP extraction
- Add failing tests for declarations, comments, and strings.
- Implement and refactor the Bicep extraction mapping.

### 4. Stabilize contracts and documentation
- Add Bicep contract fixtures and update supported-language expectations.
- Run focused tests, the full build, and formatting checks.

## Steps

- [x] Complete Task 1: Register Bicep
- [x] Complete Task 2: Add MVP metrics
- [x] Complete Task 3: Add MVP extraction
- [x] Complete Task 4: Stabilize contracts and documentation

## Notes

- The parser is bundled as `libs/tree-sitter-bicep-1.1.0.jar` for the same five targets as the TSX and Pascal JARs.
- Dependency extraction remains unsupported for Bicep in this MVP.
- The bundled binding is compiled for Java 17 compatibility and verified with the full Gradle build.
