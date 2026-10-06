---
uuid: da5cc8a0-39eb-4a2f-a6e8-75ff644a8b63
title: Restore OpenCode session cancellation state and sound mocked test reporting
status: incoming
priority: P1
points: 3
labels: chat-ui, planning, cancellation, async-tests
---

## Context

Accepted ChatUI 86385532b4f8606946555d0ada8e3fb22f35b4c3 stores a boolean abort flag
inside an atom, then looks it up on the atom and dereferences nil. The unchanged
existing OpenCode success test reproduces that rejection under a read-only exit
observer; the ordinary runner prematurely requests 0 after only the six mock
assertions. The [unsubmitted issue draft](../../../.ημ/verification/chat-ui-abort-planning/issue-draft.md)
and [design note](../../notes/chat-ui-opencode-abort-state-plan.md) retain the full
reproduction and six proposed criteria. No accepted board/card exists. Fresh
upstream intake shows zero open issues/PRs
and personal intake zero open PRs; personal issues are disabled, so zero visible
results are not independent issue-absence evidence. Foreign ownership remains
unknown; no thread inventory attempted.

## Outcome

Plan a consistent cancel-state representation and truthful async test outcomes
for the existing OpenCode `IChatSession` adapter. A mocked successful send must
reach its actual token/done/history assertions; a failed async test stays red.

## Scope

- Portable Clojure-shaped cancellation/active-generation decisions in `.cljc`
  where practical; keep state representation and admission laws pure. The
  existing CLJS adapter owns fetch, promises, timers and notification effects.
- Preserve successful send, history, token/done, abort/close/unsubscribe contract
  with reviewed operation boundaries; prove independent session isolation.
- Repair the owning asynchronous test reporting contract so actual rejection is
  observed before completion/exit. Use existing mocked tests plus bounded
  cancellation and deliberate-rejection cases; no domain rewrite or new runner
  authority. The private observer is diagnostic evidence only.

## Non-goals

No software implementation in this PR. No package-manager/lock/README/workflow
repair, provider/model/backend contact, credentials, services/browser/watchers,
shared runtime, ports, release, durable ledger semantics, UI hook redesign,
Sol/Knoxx adapter changes, or new concurrency broker. No board-state transition,
reviewer request or fabricated cold-checkout/CI/readiness claim.

## Acceptance criteria

1. Successful mocked OpenCode reply emits complete text, one done and correct
   user/assistant history. All 13 original written assertions execute, then report
   additional coverage accurately; a three-test/six-assertion summary alone is
   insufficient to prove the OpenCode body ran.
2. One cancellation representation avoids map lookup on Atom and dereference of
   nil/boolean. Pure admission decisions use portable Clojure data; JSON/native
   objects and async effects stay at the adapter edge.
3. Define admitted user-history behavior. Abort before response, mid-chunk abort,
   and close suppress forbidden late callbacks/assistant commits and
   settle cancelled work explicitly. Unsubscribe suppresses only that listener’s
   callbacks, preserving other subscribers and admitted session history. Paired
   independent sessions prove cancelling
   or closing one does not affect the other. Review subsequent-send behavior
   against the existing contract; no overlapping-send or ordering guarantee is
   invented.
4. Preserve `IChatSession` signatures, constructors/export surface, mock/Sol/Knoxx
   boundaries and existing UI hook contract; the fix must not rely on an
   unrelated backend/service change.
5. Actual test command fails nonzero on an unexpected rejected Promise. A
   deliberate rejected fixture exercises this path. No external observer,
   swallowed error, zero-assertion pass or premature success summary qualifies
   green. Genuine compile/download/tool failures stay visible.
6. Keep independent standalone installer/toolchain/lock-contract uncertainty
   explicit. The observed npm ci mismatch is diagnostic, not an established
   required upstream check. This story does not change that package policy.

## Verification

This planning candidate receives native Rheos incoming readback, actual owning
Receipt River known-kind/schema validation, baseline byte preservation, lossless
capture verification and full diff hygiene. Those checks grant no readiness.
After native planning qualification and lawful Rheos ready, introduce the pure
laws and owning tests in red, then the adapter in green. Proposed future portable
law tests use `nbb -cp src:test test/eta_mu/chat_ui/session_law_test.cljs` and
`bb --classpath src:test test/eta_mu/chat_ui/session_law_test.clj` (future files,
not commands executed now). The configured test script at the extracted package
root is
`shadow-cljs compile test && node target/test.cjs`; its install manager is unpinned.
Use a force-spawn compiler/private caches for isolated reproduction; do not run
app/dev watchers or start port 8080. Installer and pinned tooling still require
owner review. Preserve deliberate async failure evidence before accepting green.

## Risks

Boolean/map/token mismatches can be fixed superficially while leaving late
completion or reused-generation cancellation unsafe. A finally/done path can
report success before its Promise rejection is observed. Keep root-cause proof
separate from that runner behavior. Three points estimates this bounded adapter
and owning-test slice; if review shows it needs decomposition, propose breakdown
explicitly through the proper workflow. No GitHub number or unsubmitted issue
is invented as a UUID dependency.
