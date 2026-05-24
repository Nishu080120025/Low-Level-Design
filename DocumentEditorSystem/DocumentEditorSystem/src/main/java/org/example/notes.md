# Machine Coding Round — Mini Document Editor

## Level

SDE-1 (0–2 Years Experience)

## Duration

90 Minutes

## Difficulty

Medium

---

# Problem Statement

Design and implement a simplified in-memory document editor system similar to a basic text editor.

The system should support:

* document creation
* text editing
* undo last operation
* version history
* document sharing with permissions

Focus on:

* clean object-oriented design
* modular architecture
* extensible code
* separation of concerns

You are NOT required to:

* build REST APIs
* use databases
* handle concurrency
* build UI

---

# Functional Requirements

---

## 1. User Management

Each user should have:

```text
userId
name
```

The system supports 3 roles:

```text
OWNER
EDITOR
VIEWER
```

---

## 2. Document Management

Each document should contain:

```text
documentId
title
content
owner
collaborators
version history
```

Use:

```java
StringBuilder
```

for storing content internally.

---

## 3. Create Document

### API

```java
createDocument(User owner, String title);
```

### Behavior

* creates empty document
* owner automatically gets OWNER permission

---

## 4. Share Document

### API

```java
shareDocument(String documentId,
              User user,
              Role role);
```

### Rules

* only OWNER can share
* collaborators can have:

    * EDITOR
    * VIEWER

---

## 5. Edit Operations

Support the following operations:

---

### Insert Text

```java
insertText(String documentId,
           int position,
           String text,
           User user);
```

### Example

Before:

```text
Hello World;
```

Insert `"Beautiful "` at position `6`;

After:

```text
Hello Beautiful World;
```

---

### Delete Text

```java
deleteText(String documentId,
           int start,
           int end,
           User user);
```

### Example

Before:

```text
Hello World;
```

Delete `(6,11)`

After:

```text
Hello;
```

---

## 6. Permission Rules

| Role   | Read | Edit |
| ------ | ---- | ---- |
| OWNER  | Yes  | Yes  |
| EDITOR | Yes  | Yes  |
| VIEWER | Yes  | No   |

If unauthorized:

* throw exception
  OR
* print proper message

---

## 7. Undo Last Operation

Support undoing ONLY the most recent operation.

### API

```java
undo(String documentId)
```

### Undo Should Work For

* insert
* delete

Single-level undo is enough.

---

## 8. Version History

After every successful edit:

* store document snapshot as new version

Each version should contain:

```text
versionNumber
contentSnapshot
```

### API

```java
getVersions(String documentId);
```

### Optional

```java
restoreVersion(documentId, versionNo);
```

(Discussion-only if time permits.)

---

# Constraints

```text
In-memory only
No database required
No concurrency required
No multithreading required
No networking required
```

---

# Expected Class Design

Candidate is expected to identify proper entities and services.

---

## Suggested Entities

```text
User
Document
Version
```

---

## Suggested Enums

```text
Role
```

---

## Suggested Services

```text
DocumentService
UndoService
```

---

## Suggested Commands

```text
EditCommand
InsertCommand
DeleteCommand
```

---

## Suggested Repository

```text
DocumentRepository
```

Use in-memory HashMap storage.

---

# Suggested Driver Flow

```text
1. Create users
2. Create document
3. Share document with editor
4. Insert text
5. Delete text
6. Undo last operation
7. Print version history
```

---

# Expected Design Thinking

Interviewers will evaluate:

* object-oriented design
* separation of concerns
* extensibility
* naming clarity
* clean code
* edge case handling

---

# Edge Cases To Handle

Candidate should reasonably handle:

* invalid documentId
* invalid positions
* negative indices
* undo when no operations exist
* viewer attempting edit
* empty insertion text
* deleting out of bounds range

---

# Expected Design Patterns

## 1. Command Pattern (Highly Recommended)

Useful for:

* undo support
* encapsulating edit operations

### Expected Structure

```text
EditCommand
    -> execute()
    -> undo()
```

### Concrete Commands

```text
InsertCommand
DeleteCommand
```

---

## 2. Factory Pattern (Optional Bonus)

If candidate wants cleaner command creation.

Not mandatory.

---

# What Interviewer EXPECTS in 90 Minutes

## MUST HAVE

Strongly expected:

* entities
* document editing
* permission validation
* undo support
* version snapshots
* clean class structure

---

## GOOD TO HAVE

Positive signals:

* command pattern
* service separation
* exception handling
* modular architecture
* extensible design

---

## NOT EXPECTED

Do NOT over-engineer.

Interviewers do NOT expect:

* CRDT
* Operational Transform
* Real-time collaboration
* Database persistence
* WebSockets
* Distributed systems
* Full Google Docs clone

---

# Evaluation Criteria

| Area               | Weightage |
| ------------------ | --------- |
| OOP Design         | 30%       |
| Correctness        | 25%       |
| Clean Architecture | 20%       |
| Edge Cases         | 15%       |
| Communication      | 10%       |

---

# Ideal Time Breakdown (Interview)

| Task                      | Time    |
| ------------------------- | ------- |
| Requirement understanding | 10 mins |
| Design discussion         | 15 mins |
| Entities + models         | 10 mins |
| Core APIs                 | 25 mins |
| Undo + versions           | 15 mins |
| Testing + cleanup         | 10 mins |
| Buffer                    | 5 mins  |

---

# What A Strong SDE-1 Candidate Usually Delivers

Strong candidates typically:

* identify entities quickly
* separate service layer properly
* use StringBuilder
* use command pattern for undo
* avoid giant classes
* handle edge cases
* explain tradeoffs clearly

---

# Follow-Up Discussion Questions

Interviewer may ask:

1. How would you support redo?
2. How would you support concurrent editing?
3. Why command pattern?
4. Why store versions separately?
5. How would you optimize memory usage?
6. How would Google Docs handle simultaneous edits?

You are NOT expected to implement these.

---

# Realistic Time Expectation For Beginners

If you are NEW to LLD:

| Stage                                | Expected Time |
| ------------------------------------ | ------------- |
| Understanding architecture           | 2–4 hours     |
| Designing classes properly           | 1–2 hours     |
| Understanding command pattern deeply | 2–5 hours     |
| First full implementation            | 6–10 hours    |
| Clean refactor                       | 2–3 hours     |

So first serious attempt may realistically take:

```text
1–2 days of focused effort
```

And that is completely normal for beginners.

---

# Important Note For Beginners

This problem is difficult mainly because of:

* responsibility separation
* ownership modeling
* undo architecture
* state vs behavior distinction

NOT because of coding complexity.

The actual implementation is moderate once architecture becomes clear.

---

# What You Should Focus On While Practicing

Initially focus more on:

* WHY each class exists
* WHY each responsibility lives where it does

instead of:

* finishing within 90 mins

Speed comes naturally after solving multiple LLD problems.



how we can create a permission list where we can specify what action can owner can do and what action editor can do and what action viewer can do. We can create a Permission class that has a list of allowed actions for each role. Then we can check the user's role against the permission list before allowing them to perform an action.


// How  i can use Command as an interface and then change it to insert delete and all how can i use the design pattern tell me?



