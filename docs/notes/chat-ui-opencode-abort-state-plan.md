# Proposed ChatUI OpenCode abort-state repair — no implementation

Story `da5cc8a0-39eb-4a2f-a6e8-75ff644a8b63`, three points, incoming. Accepted and
mapped personal source 86385532b4f8606946555d0ada8e3fb22f35b4c3. No issue/PR number is
invented; the full unsubmitted issue draft retains six acceptance criteria.

## Exact observed seam

`src/eta_mu/chat_ui/opencode_session.cljs` state is an Atom holding messages and
a boolean :aborted; line 45 passes `(:aborted state)` to line 28's dereference and
line 46 also dereferences that atom lookup. Native original mocked-test observation
rejects with missing IDeref on null at lines 28/45 and testline 26. Source/compiled
bytes remain unchanged. The ordinary run prints 3 tests / 6 assertions / 0 errors; two mock
tests supply those six, while seven OpenCode assertions are not reached. Compiled
async wrapper finally invokes done, and the generated Node test runner exits 0
before reporting its Promise rejection. An external read-only 250 ms exit-delay
observer captures one actual rejection and returns 1. This does not claim the
original test command failed and will not substitute for sound owning tests.

## Semantic and effect boundary

Propose pure `.cljc` laws for explicit cancellation/active-generation admission,
using Clojure-shaped state and event decisions. Choose a single representation
in review (e.g. boolean in state with pure reads, or per-send cancellation token
at the adapter edge); do not merely replace one lookup with a dereference of a
boolean. Preserve admitted user input/history and define when a cancelled/closed
send may no longer emit tokens/done or commit assistant history. Paired independent
sessions must remain isolated. Review subsequent sends against the existing
contract without inventing arbitrary overlapping-send or ordering semantics.
No concurrent-send broker or durable event model.

Existing CLJS adapter retains fetch, response conversion, Promise/timer lifetime,
notification and cleanup effects. Unsubscribe removes only its listener; it
preserves other subscribers and admitted history rather than cancelling the
whole session. Native/JSON values stay outer. Public
`IChatSession` methods and constructors remain compatible; no hook, Sol, Knoxx
or provider runtime change is assumed. Error rejection is distinct from success,
and cancellation settles explicitly. No fabricated successful completion.

## Red and green proposal

After exact-head planning qualification and lawful Rheos readiness, preserve the
original seven OpenCode success assertions and six mock assertions, then add
bounded before-response/mid-chunk cancellation, close/unsubscribe and independent-
session cases. Review existing subsequent-send behavior and lawful history
admission with those tests, without a new overlapping-send contract. Add deliberate async
rejection to prove the actual owning command is nonzero; do not waive a premature
finally/done success path or claim an external observer is the final gate.

Pure law tests should run NBB and Babashka with future paths in the card. The
owning compiled Node script remains `shadow-cljs compile test && node target/test.cjs`;
local bounded force-spawn compilation uses private JVM/Maven/cache/tmp roots,
not a watch/HTTP server. No app/lib/build policy is changed here. Compilation,
assertion counts, errors and exit codes must all remain observable.

## Separate installer-contract limitation

The actual accepted package-lock graph differs from package.json (marked 12 vs4,
missing DOMPurify and its trusted-types dependency). Exact copied-lock `npm ci
--ignore-scripts` failed EUSAGE. Source has no AGENTS, local skills, CI, manager
pin, Node/npm pin or pnpm-lock; README names pnpm in an old workspace. This does
not prove a required npm ci gate failed or authorize a package-manager switch.
Diagnostic manifest install with hooks disabled in a private runtime, source-lock
Shadow 3.4.11, React 18.3.1, marked 4.3.0, DOMPurify 3.4.16, scheduler 0.23.2, Node 24.14.1
and Java 21.0.12.1 enabled the observed test failure. It does not qualify a cold
checkout or release. A separate unsubmitted standalone install/build/test contract
draft sits outside this candidate; no lock/policy/README repair is included.

## Planning and authority holds

No accepted board config/cards exist and no neighbour style is available. New
first-class Markdown is incoming input only. Native default-root reads can
materialize empty private ledgers; retain them without claiming historical
admission. Explicit UUID is identity; no invented foreign hard dependencies.
No native planning rounds/approval or lawful readiness exists yet. All source,
package/lock, test/config and provenance bytes remain unchanged; this candidate
adds planning and owned evidence only. Root exact-head peer precedes any issue,
push or personal PR publication; manual reviewers remain root-coordinated.
