Bug report for `open-hax/chat-ui`; no assignment or closure claim.
Accepted source `86385532b4f8606946555d0ada8e3fb22f35b4c3` and mapped personal main
are identical. Fresh upstream intake shows zero open issues/PRs; personal intake
shows zero open PRs. Personal issues are disabled, so zero visible results do not
prove independent issue absence. Foreign ownership remains unknown.

## Observed problem

`create-opencode-session` stores `{:messages [] :aborted false}` inside a state
atom. `send-message!` passes `(:aborted state)` to `emit-chunks!`, which dereferences
it; the caller also dereferences `(:aborted state)`. Keyword lookup is on the
atom rather than its value, producing nil. Reading `@state` alone would yield a
boolean, which likewise is not the dereferenceable token expected by that helper.

The unchanged existing `opencode-session-emits-token-and-done` test at line 26
rejects with `No protocol method IDeref.-deref defined for type null`, from
`opencode_session.cljs:28:38` and `:45:14`. No real provider/backend request runs:
the original test supplies its own mocked fetch. A guard rejects unexpected real
fetch in the diagnostic observer.

## Reproduction and limits

The original Shadow 3.4.11 compile/test autorun prints 3 tests / 6 assertions / 0 failures /
0 errors and requests exit 0. Those six assertions are in the two mock-session
tests; the seven written OpenCode assertions are not reached. Its generated async
wrapper calls done in finally; the Node runner exits before the rejected Promise
is reported. A private read-only observer requires the unchanged compiled test
bundle, records original exit 0, delays only exit 250 ms and captures one native
unhandled test rejection, then exits1. It changes neither test logic, source nor
compiler artifacts and is not a claim that the original test command failed.

Compilation uses a private diagnostic manifest-based runtime with Shadow 3.4.11
matching source-lock compiler, Node 24.14.1 and Java 21.0.12.1. Exact source-lock
`npm ci --ignore-scripts` first failed EUSAGE because its dependency graph differs
from package.json. Accepted source has no CI or pinned install manager/Node/npm;
README uses pnpm workspace commands. Thus this is no failed required-check claim
or mandate to change package manager. Diagnostic runtime proves the mocked
adapter error only; standalone cold-checkout qualification remains unresolved.
Raw commands, streams, tuple and source/compiled hashes are in the evidence note.

## Proposed acceptance criteria

1. Successful mocked send delivers full token text, exactly one done event and
   correct user/assistant history; all 13 currently written assertions execute
   (six mock plus seven OpenCode), with additional coverage reported honestly.
2. Use one explicit cancellation representation consistently; no lookup on an
   atom as if a map, and no dereference of nil or plain booleans. Keep portable
   Clojure data/decision laws separate from promise/fetch/timer effects.
3. Abort before response or during chunk delivery and close do not
   produce later forbidden token/done callbacks or assistant-history commits;
   cancelled sends settle explicitly without a dangling Promise. Unsubscribe
   removes only that listener’s callbacks without cancelling other subscribers
   or suppressing admitted session history. Paired independent
   sessions prove cancelling/closing one does not affect the other. Define the
   admitted user-history behavior and operation boundary in reviewed existing laws.
4. Review subsequent-send behavior against the existing contract; no arbitrary
   overlapping-send semantics or ordering guarantee is invented. Preserve `IChatSession`,
   exported constructors, current mock/Sol/Knoxx boundaries and UI hook contract;
   no provider transport rewrite, new concurrency broker or durable ledger.
5. Unexpected async rejection in the owning tests must produce nonzero outcome
   through the actual repository test command. A deliberate rejected fixture
   verifies this; no `process.exit(0)`/summary-only waiver or external observer
   substitutes for the final owning test contract.
6. Keep the standalone manager/toolchain/lock contract separate and unresolved
   until reviewed. No package-policy/lock/README/workflow repair in this abort
   slice; no real network/provider, credentials, service, release or deployment.

Planning story 3 pt, incoming UUID `da5cc8a0-39eb-4a2f-a6e8-75ff644a8b63` is proposed
only. Native planning qualification and lawful Rheos readiness precede software
implementation; the planning candidate remains local and no PR is published.
