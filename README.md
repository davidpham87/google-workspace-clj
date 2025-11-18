# google-workspace-clj [WIP]
Calling Google Workspace from Clojure.

Will be mostly vibe coded.

# Jules Instructions

## Goals

1. Provide a function to parse discovery schema from Google Workspace api. Start with Google Keep.
   a. Use malli to create a schema of the schema files for tduscovery.
2.From the paraed configuration, create a macro that will define function from the paraed schema.

## Constraints
1. Minimal dependencies, only use babashka built-ins, with the additiok of metosin/malli
2. Should be correct

## Testing

1. Schema parsing: thanks to the schema from malli, generate round trip: malli schema -> clojure data -> schema -> clojure data. Ensure clojure-data are equal, but that the schema also is a valid schema.

Plan:

1. Write a function that parse discovery schema from google apis using malli schema.
2. Write function/macro that accepts the output from the previous steps and create functions to call the excpeted behaviorb (such as read-note, find nodes, etc).

Keep:
https://keep.googleapis.com/$discovery/rest?version=v1
