(require '[eta-mu.receipt-river.api :as rr]
         '[eta-mu.receipt-river.shape.edn :as edn]
         '[clojure.string :as str]
         '["node:fs" :as fs])
(def target "/tmp/foresight-chat-ui-abort-planning-8px3s7dh/worktree/.ημ/receipts.edn")
(assert (not (fs/existsSync target)) "never duplicate initial appends")
(def source "86385532b4f8606946555d0ada8e3fb22f35b4c3")
(def ts (.toISOString (js/Date.)))
(doseq [[id kind note tests] [["ac2f1d9f-0c3f-4992-8c18-73ad4f0a168b" :observation
                             "Accepted/personal source identical, native open issues/PRs0. Isolated planning only; cancellation representation mismatch reproduced in unchanged existing mocked test. No software implementation, issue publication, reviewer request, backend or global settings effects."
                             "Original compile autorun0:3tests6assertions0failures0errors; seven Opencode assertions not reached. Private observer1 retains compiled hashes and captures native null IDeref at adapter28/45,test26; diagnostic not final gate. Exact copied-lock npmci1 EUSAGE; no established manager/CI pin. Private diagnostic dependencies only."]
                            ["17089718-bd22-4baf-9045-098c97b36b39" :test-run
                             "Actual canonical private Rheos ef3 reads proposed UUID incoming/total1; empty private event ledger retained untracked. Three-point plan and unsubmitted six-criterion issue draft preserve send/history/listener/cancel boundaries, independent sessions, async-rejection nonzero requirement and separate install uncertainty. Planning/readiness remain unqualified."
                             "Native read-board/read-task0, no transitions. Owning ReceiptRiver154440 API validates both declared-schema known-kind records. Baseline23trackedblobs unchanged; reproduction23 unchanged; before/after compiled3sha256 exact; lossless captures retain failures distinct from successes. No source tests rerun after documentation-only preparation."]]]
 (let [event (rr/build-event {:event-id (uuid id) :recorded-at ts :component-manifest {:eta-mu/version "1.1.1"} :command "chat-ui-abort-planning-local-evidence" :subject {:repo "open-hax/chat-ui" :commit source :story "da5cc8a0-39eb-4a2f-a6e8-75ff644a8b63"}}
              {:ts ts :kind kind :repo "open-hax/chat-ui" :origin "isolated-chat-ui-abort-planning" :owner "root/issues" :dod "reviewable incoming proposal; no implementation admission" :pi "foresight-parallel-goal" :host "private-temp-root" :manifest ["docs/agile/tasks/chat-ui-opencode-abort-state.md" "docs/notes/chat-ui-opencode-abort-state-plan.md" ".ημ/verification/chat-ui-abort-planning/issue-draft.md"] :refs [source "da5cc8a0-39eb-4a2f-a6e8-75ff644a8b63"] :note note :tests tests})
       line (edn/format-line event) result (rr/validate-line line 1)]
  (assert (:ok result) (pr-str (:errors result)))
  (assert (= :declared (get-in result [:source/schema :status])))
  (fs/appendFileSync target (str line "\n"))
  (println (pr-str {:id id :kind kind :ok (:ok result) :schema (:source/schema result)}))))
